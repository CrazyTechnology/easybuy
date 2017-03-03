package com.easybuy.ming.sso.login.controller;

import com.easybuy.ming.sso.login.service.LoginService;
import com.easybuy.ming.utils.EasybuyResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-03-03.
 */
@Controller
@RequestMapping("/sso")
public class LoginController {
    Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    @RequestMapping("/toLogin.do")
    public ModelAndView toLogin(){
        logger.info("跳转到登录首页。。。");
        return new ModelAndView("login");
    }

    @RequestMapping("/toRegister.do")
    public ModelAndView toRegister(){
        logger.info("跳转到注册页面。。。");
        return new ModelAndView("register");
    }


    @RequestMapping(value="/login", method= RequestMethod.POST)
    @ResponseBody
    public EasybuyResult login(String username, String password,
                               HttpServletRequest request, HttpServletResponse response) {
        EasybuyResult result = loginService.login(username, password, request, response);
        return result;
    }

    @RequestMapping("/user/page/login")
    public String showRegister(String redirect, Model model) {
        //把url参数传递到jsp
        model.addAttribute("redirect", redirect);
        return "login";
    }
}
