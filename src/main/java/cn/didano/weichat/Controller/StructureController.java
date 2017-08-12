package cn.didano.weichat.Controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.didano.weichat.repository.WxMpInMemoryConfigStorageContainer;
import cn.didano.weichat.util.CommonUtil;
import cn.didano.weichat.util.ContextUtil;
import cn.didano.weichat.util.Oauth2Test;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceApacheHttpClientImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/structure")
public class StructureController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ApiOperation(value = "通过微信后台获取当前用户", notes = "通过微信后台获取当前用户")
	@GetMapping(value = "/babycenter")
	public ModelAndView getAuthWxMpUser(HttpServletRequest request) {

		String oriId = request.getParameter("oriid");
		// String oriId= "gh_c0a5d7478a57";

		String code = request.getParameter("code");
		logger.info("oriId:" + oriId);
		logger.info("code:" + code);
		ModelAndView modelAndView = new ModelAndView("babycenter");
		if (oriId == null || code == null) {
			logger.warn("oriId数据为:"+oriId+" ,或code数据为:"+code);
			return modelAndView;
		}
		try {
			WxMpService wxMpService = ContextUtil.act.getBean(WxMpService.class);
			wxMpService.setWxMpConfigStorage(WxMpInMemoryConfigStorageContainer.findByOriId(oriId));

			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			
			//WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
			//wxMpOAuth2AccessToken = wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
			
			modelAndView.addObject("openid",wxMpOAuth2AccessToken.getOpenId());
			
			logger.info("openid数据是: " + wxMpOAuth2AccessToken.getOpenId());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		// response.getWriter().flush();
		// response.getWriter().close();
		// return null;
		return modelAndView;
	}
	
	
	@ApiOperation(value = "测试1", notes = "测试1")
	@GetMapping(value = "/getAuthOpenidTest1")
	public String getAuthOpenidTest1() {
		String code = null;
		try {
			WxMpService wxMpService = ContextUtil.act.getBean(WxMpService.class);
			wxMpService.setWxMpConfigStorage(WxMpInMemoryConfigStorageContainer.findByOriId("gh_c0a5d7478a57"));

			String source = "www.didano.cn/wechat_v2/structure/babycenter?oriid=gh_c0a5d7478a57";
			String redirectURI = CommonUtil.urlEncodeUTF8(source);
			String scope = "snsapi_base"; 
			String state = "state";
			System.out.println(redirectURI);
			 
			 System.out.println("123456");
			 String url = wxMpService.oauth2buildAuthorizationUrl(redirectURI, scope, state);
			 
			System.out.println("url地址值为:" + url);
			
			code = wxMpService.getOAuth2Code(url);
			logger.info("获取code的数据为:"+code);
			
			//WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
			//wxMpOAuth2AccessToken = wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
			
			
			logger.info("code数据是: " + code);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return code;
	}
	
	@ApiOperation(value = "测试2", notes = "测试2")
	@GetMapping(value = "/getAuthOpenidTest2")
	public void getAuthOpenidTest2() {
		System.out.println("11");
		Oauth2Test test2 = new Oauth2Test();
		test2.oauth2GetCode2();
	}

}
