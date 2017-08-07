package cn.didano.weichat.Controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.didano.weichat.repository.WxMpInMemoryConfigStorageContainer;
import cn.didano.weichat.util.ContextUtil;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/structure")
public class StructureController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * @GetMapping("/babycenter") public ModelAndView babycenter(@RequestParam(name
	 * = "oriid", required = false) String oriid) { AuthUtil authUtil = new
	 * AuthUtil();
	 * 
	 * try { ExecutorService executorService = Executors.newFixedThreadPool(1);
	 * Runnable runnable = new Runnable() {
	 * 
	 * @Override public void run() { try {
	 * System.out.println("=====================1");
	 * System.out.println(Thread.currentThread().getName() + ": testRetry");
	 * authUtil.initAuth(oriid); System.out.println("=====================2"); }
	 * catch (Exception e) { e.printStackTrace(); } } }; Future<?> submit1 =
	 * executorService.submit(runnable); submit1.get(); } catch (Exception e) { //
	 * TODO: handle exception } return new ModelAndView("index"); }
	 */

	@ApiOperation(value = "获取当前用户", notes = "获取当前用户")
	@GetMapping(value = "/babycenter")
	public ModelAndView getAuthWxMpUser(HttpServletRequest request) {

		String oriId = request.getParameter("oriid");
		// String oriId= "gh_c0a5d7478a57";

		String code = request.getParameter("code");
		logger.info("oriId:" + oriId);
		logger.info("code:" + code);
		ModelAndView modelAndView = new ModelAndView("babycenter");
		if (oriId == null || code == null) {
//			modelAndView.addObject("openid","123");
			return modelAndView;
		}
		try {
			// response.getWriter().println("<h1>code</h1>");
			// response.getWriter().println(code);
			logger.info("-------------getCode-----------------");

			WxMpService wxMpService = ContextUtil.act.getBean(WxMpService.class);
			wxMpService.setWxMpConfigStorage(WxMpInMemoryConfigStorageContainer.findByOriId(oriId));

			WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
			WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
			wxMpOAuth2AccessToken = wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
			logger.info("-------------rtoken-----------------");
			System.out.println("getAuthWxMpUser session:" + request.getSession().getId());
			// request.getSession().setAttribute("wxMpUser", wxMpUser);
			modelAndView.addObject("wxMpUser", wxMpUser);
//			modelAndView.addObject("openid", wxMpUser.getOpenId());
			
			modelAndView.addObject("openid",wxMpUser.getOpenId());
			
			logger.info("openid数据是: " + wxMpUser.getOpenId());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		// response.getWriter().flush();
		// response.getWriter().close();
		// return null;
		return modelAndView;
	}

	/*
	 * @Autowired private WxMpInMemoryConfigStorageContainer wxMpInfoRepository;
	 */

	/*
	 * @GetMapping(produces = "text/plain;charset=utf-8") public ModelAndView
	 * authGet(
	 * 
	 * @RequestParam(name = "signature", required = false) String signature,
	 * 
	 * @RequestParam(name = "timestamp", required = false) String timestamp,
	 * 
	 * @RequestParam(name = "nonce", required = false) String nonce,
	 * 
	 * @RequestParam(name = "echostr", required = false) String echostr) {
	 * 
	 * this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp,
	 * nonce, echostr); ModelAndView view = new ModelAndView();
	 * 
	 * String redirectURI="http://www.didano.cn/wechat_v2/OAuthServlet"; String
	 * scope = "snsapi_userinfo"; String state = "state"; WxMpServiceImpl
	 * wxMpServiceImpl = ContextUtil.act.getBean(WxMpServiceImpl.class); String url
	 * = wxMpServiceImpl.oauth2buildAuthorizationUrl(redirectURI,scope,state);
	 * 
	 * 
	 * public WxMpUser getWxMpUser() { System.out.println(url);
	 * 
	 * 
	 * return null; }
	 * 
	 * if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) { throw new
	 * IllegalArgumentException("请求参数非法，请核实!"); }
	 * 
	 * if (this.wxService.checkSignature(timestamp, nonce, signature)) { return
	 * echostr; }
	 * 
	 * return ""; }
	 */

	/*
	 * 确定是哪个微信公众号的方法;
	 */
	/*
	 * public Hand_officialaccount ensureOfficialaccount(String originalId) {
	 * List<Hand_officialaccount> storage = wxMpInfoRepository.init();
	 * Hand_officialaccount hand_officialaccount = null; if (storage != null &&
	 * storage.size() > 1) { for (Hand_officialaccount officialaccount : storage) {
	 * if (originalId == officialaccount.getOriginalId()) { hand_officialaccount =
	 * officialaccount; } } } return hand_officialaccount; }
	 */

	/*
	 * @GetMapping("/babycenter") public ModelAndView LinuxEnvTemperatureInfoGraph()
	 * { return new ModelAndView("babycenter"); }
	 */
}
