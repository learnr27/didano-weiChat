package cn.didano.weichat.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.didano.weichat.repository.WxMpInMemoryConfigStorageContainer;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

public class AuthUtil {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public void initAuth(String oriId) {
		String redirectURI = "http://www.didano.cn/wechat_v2/structure/getAuthUser?oriid=gh_c0a5d7478a57";
		//String scope = "snsapi_userinfo";
		String scope = "snsapi_base";
		String state = "state";
		WxMpServiceImpl wxMpServiceImpl = ContextUtil.act.getBean(WxMpServiceImpl.class);
		WxMpService wxMpService = ContextUtil.act.getBean(WxMpService.class);
		wxMpService.setWxMpConfigStorage(WxMpInMemoryConfigStorageContainer.findByOriId(oriId));
		String url = wxMpServiceImpl.oauth2buildAuthorizationUrl(redirectURI, scope, state);

		logger.info("url连接地址:" + url);
		try {
			wxMpService.post(url, "");
		} catch (WxErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
