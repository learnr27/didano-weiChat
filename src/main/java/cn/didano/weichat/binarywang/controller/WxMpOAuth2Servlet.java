package cn.didano.weichat.binarywang.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.didano.weichat.Controller.LoginController;
import cn.didano.weichat.binarywang.handler.MenuHandler;
import cn.didano.weichat.repository.WxMpInMemoryConfigStorageContainer;
import cn.didano.weichat.util.ContextUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

@WebServlet(name = "WxMpOAuth2Servlet",urlPatterns = "/wxMpOAuth2Servlet")
public class WxMpOAuth2Servlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  protected final Logger log = LoggerFactory.getLogger(this.getClass());

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException {

    response.setContentType("text/html;charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);
    String oriId= request.getParameter("oriid");
//  String oriId= "gh_c0a5d7478a57";

    String code = request.getParameter("code");
    log.info("oriId:"+oriId);
    log.info("code:"+code);
    try {
      //response.getWriter().println("<h1>code</h1>");
      //response.getWriter().println(code);
      log.info("-------------getCode-----------------");
      
      WxMpService wxMpService = ContextUtil.act.getBean(WxMpService.class);
      wxMpService.setWxMpConfigStorage(WxMpInMemoryConfigStorageContainer.findByOriId(oriId));
      
      WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
      
      //response.getWriter().println("<h1>access token</h1>");
      //response.getWriter().println(wxMpOAuth2AccessToken.toString());
      log.info("-------------token-----------------");

      WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
      //response.getWriter().println("<h1>user info</h1>");
      //response.getWriter().println(wxMpUser.toString());
      log.info("-------------userinfo-----------------");

      //wxMpOAuth2AccessToken = wxMpService.oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken());
      //response.getWriter().println("<h1>after refresh</h1>");
      //response.getWriter().println(wxMpOAuth2AccessToken.toString());
      log.info("-------------rtoken-----------------");
      request.getSession().setAttribute("wxMpUser",wxMpUser);
    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    //response.getWriter().flush();
    //response.getWriter().close();

  }

}
