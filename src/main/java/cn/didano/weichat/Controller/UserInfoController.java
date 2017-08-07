package cn.didano.weichat.Controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "获取微信用户服务", tags = "获取微信用户服务")
@RestController
@RequestMapping(value = "/Userinfo")
public class UserInfoController {
	
	static Logger logger = Logger.getLogger(UserInfoController.class);
	
	/*String source="http://www.didano.cn/wechat_v2/OAuthServlet";
	String redirectURI= CommonUtil.urlEncodeUTF8(source);
    String scope = "snsapi_userinfo";
    String state = "state";
    WxMpServiceImpl wxMpServiceImpl = ContextUtil.act.getBean(WxMpServiceImpl.class);
    String url = wxMpServiceImpl.oauth2buildAuthorizationUrl(redirectURI,scope,state);
    
    
	public WxMpUser getWxMpUser() {
        System.out.println(url);
        
		
		return null;
	} */
	
	
}
