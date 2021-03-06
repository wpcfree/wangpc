package com.wpc.common.base.controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wpc.common.SessionUtil;
import com.wpc.common.msg.AjaxResult;
import com.wpc.common.security.shiro.MyFormAuthenticationFilter;
import com.wpc.common.security.shiro.ShiroRealm.Principal;
import com.wpc.common.utils.image.CaptchaUtils;
import com.wpc.common.utils.image.vcode.Captcha;
import com.wpc.common.utils.image.vcode.GifCaptcha;
import com.wpc.common.utils.image.vcode.SpecCaptcha;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferedImage;

@Controller
@RequestMapping(value = "/")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MyFormAuthenticationFilter formAuthenticationFilter;
    @Autowired
    private SessionDAO sessionDAO;

	@RequestMapping(value="/login", method = RequestMethod.GET)
    public String login() {
        return "login2";
    }
    
//    @RequestMapping(value = "/logout")
//    public String doLogout() {
//        logger.info("======用户"+ SessionUtil.getUser().getLoginName()+"退出了系统");
//        SecurityUtils.getSubject().logout();
//        return "login2";
//    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }
  
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, Model model) {

        Principal principal = SessionUtil.getPrincipal();
        if (principal != null) {
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            // 获取保存的URL
            if (savedRequest == null || savedRequest.getRequestUrl() == null) {
                return "redirect:/";
            } else {
                //String url = savedRequest.getRequestUrl().substring(12, savedRequest.getRequestUrl().length());
            	return "redirect:" + savedRequest.getRequestUrl();
            }
        }

        String username = WebUtils.getCleanParam(request, formAuthenticationFilter.getUsernameParam());
        String password = WebUtils.getCleanParam(request, formAuthenticationFilter.getPasswordParam());
        boolean rememberMe = WebUtils.isTrue(request, formAuthenticationFilter.getRememberMeParam());
        boolean mobile = WebUtils.isTrue(request, formAuthenticationFilter.getMobileLoginParam());
        String message = (String)request.getAttribute(formAuthenticationFilter.getMessageParam());
        String exception = (String)request.getAttribute(MyFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);

        if (StringUtils.isBlank(message) || StringUtils.equals(message, "null")){
            message = "用户或密码错误, 请重试.";
        }

        model.addAttribute(formAuthenticationFilter.getUsernameParam(), username);
        model.addAttribute(formAuthenticationFilter.getPasswordParam(), password);
        model.addAttribute(formAuthenticationFilter.getRememberMeParam(), rememberMe);
        model.addAttribute(formAuthenticationFilter.getMobileLoginParam(), mobile);
        model.addAttribute(formAuthenticationFilter.getMessageParam(), message);
        model.addAttribute(MyFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exception);

        if (logger.isDebugEnabled()){
            logger.debug("login fail, active session size: {}, message: {}, exception: {}",
                    sessionDAO.getActiveSessions().size(), message, exception);
        }
        
        return "login2";
    }

    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @RequestMapping(value="getGifCode",method=RequestMethod.GET)
    public void getGifCode(HttpServletResponse response){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,42,4);
            //输出
            ServletOutputStream out = response.getOutputStream();
            captcha.out(out);
            out.flush();
            //存入Shiro会话session
//            TokenManager.setVal2Session(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase());
        } catch (Exception e) {
            logger.debug("获取验证码异常：{}", e.getMessage());
        }
    }
    /**
     * 获取验证码（jpg版本）
     * @param response
     */
    @RequestMapping(value="getJPGCode",method=RequestMethod.GET)
    public void getJPGCode(HttpServletResponse response,HttpServletRequest request){
        CaptchaUtils.captcha(100, 40, 4, CaptchaUtils.CAPTCHA_TYPE.ALL, request, response);
        /*try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");
            *//**
             * jgp格式验证码
             * 宽，高，位数。
             *//*
            Captcha captcha = new SpecCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());

            //存入Session
//            SessionUtil.getSession().setAttribute("_code",captcha.text().toLowerCase());
        } catch (Exception e) {
//            LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
        }*/
    }
    
}
