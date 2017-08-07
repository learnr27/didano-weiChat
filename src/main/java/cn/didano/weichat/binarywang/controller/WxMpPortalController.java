package cn.didano.weichat.binarywang.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.weichat.binarywang.service.WeixinService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

/**
 * @author Binary Wang
 */
@RestController
@RequestMapping("/auth/signature")
public class WxMpPortalController {
	@Autowired
	private WeixinService wxService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ResponseBody
	@GetMapping(produces = "text/plain;charset=utf-8")
	public String authGet(@RequestParam(name = "signature", required = false) String signature,
			@RequestParam(name = "timestamp", required = false) String timestamp,
			@RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "echostr", required = false) String echostr,
			@RequestParam(name = "oriid", required = false) String oriid) {
		this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);
		logger.info("WxMpPortalController authGet ...signature的数据为:"+signature);
		logger.info("WxMpPortalController authGet ...timestamp的数据为:"+timestamp);
		logger.info("WxMpPortalController authGet ...nonce的数据为:"+nonce);
		logger.info("WxMpPortalController authGet ...echostr的数据为:"+echostr);
		logger.info("WxMpPortalController authGet ...oriid的数据为:"+oriid);
		

		if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
			throw new IllegalArgumentException("请求参数非法，请核实!");
		}
		logger.info("authGet01");

		if (this.getWxService(oriid).checkSignature(timestamp, nonce, signature)) {
			logger.info("WxMpPortalController checkSignature ...echostr的数据为:"+echostr);
			return echostr;
		}
		logger.info("authGet02");

		return "非法请求";
	}

	@ResponseBody
	@PostMapping(produces = "application/xml; charset=UTF-8")
	public String post(@RequestBody String requestBody, @RequestParam("signature") String signature,
			@RequestParam(name = "encrypt_type", required = false) String encType,
			@RequestParam(name = "msg_signature", required = false) String msgSignature,
			@RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce,
			@RequestParam("oriid") String oriid) {
		this.logger.info(
				"\n接收微信请求：[signature=[{}], encType=[{}], msgSignature=[{}],"
						+ " timestamp=[{}], nonce=[{}], requestBody=[\n{}\n] ",
				signature, encType, msgSignature, timestamp, nonce, requestBody);
		logger.info("oriId的数据为:"+oriid);
		
		if (!this.getWxService(oriid).checkSignature(timestamp, nonce, signature)) {
			throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
		}

		String out = null;
		if (encType == null) {
			// 明文传输的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromXml(requestBody);
			WxMpXmlOutMessage outMessage = this.getWxService(oriid).route(inMessage);
			if (outMessage == null) {
				return "";
			}

			out = outMessage.toXml();
		} else if ("aes".equals(encType)) {
			// aes加密的消息
			WxMpXmlMessage inMessage = WxMpXmlMessage.fromEncryptedXml(requestBody,
					this.getWxService(oriid).getWxMpConfigStorage(), timestamp, nonce, msgSignature);
			this.logger.debug("\n消息解密后内容为：\n{} ", inMessage.toString());
			WxMpXmlOutMessage outMessage = this.getWxService(oriid).route(inMessage);
			if (outMessage == null) {
				return "";
			}

			out = outMessage.toEncryptedXml(this.getWxService(oriid).getWxMpConfigStorage());
		}

		this.logger.debug("\n组装回复信息：{}", out);

		return out;
	}

	protected WeixinService getWxService(String oriid) {
		wxService.init(oriid);
		return this.wxService;
	}

}
