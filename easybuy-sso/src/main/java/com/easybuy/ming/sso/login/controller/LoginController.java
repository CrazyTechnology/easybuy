package com.easybuy.ming.sso.login.controller;

import com.easybuy.ming.pojo.TbUser;
import com.easybuy.ming.sso.login.service.LoginService;
import com.easybuy.ming.utils.EasybuyResult;
import com.easybuy.ming.utils.JsonUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.support.UiApplicationContextUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017-03-03.
 */
@Controller
@RequestMapping("/sso")
public class LoginController {
    Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private LoginService loginService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/toLogin.do")
    public ModelAndView toLogin(){
        logger.info("跳转到登录首页。。。");
        return new ModelAndView("login");
    }

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public EasybuyResult login(HttpServletRequest request,HttpServletResponse response,String username,String password){
        EasybuyResult result = loginService.login(username,password,request,response);
        return result;
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/toRegister.do")
    public ModelAndView toRegister(){
        logger.info("跳转到注册页面。。。");
        return new ModelAndView("register");
    }


    /**
     * 根据token查询用户信息
     * @param token 用户唯一标示
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/user/token.do", method= RequestMethod.POST)
    @ResponseBody
    public String login(String token,  HttpServletRequest request, HttpServletResponse response) {
        EasybuyResult result = loginService.getLoginInfo(token,request, response);
        return JsonUtils.object2json(result);
    }

    /**
     * 根据token查询用户信息
     * @param token 用户唯一标示
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/user/logout.do", method= RequestMethod.POST)
    @ResponseBody
    public String logout(String token,  HttpServletRequest request, HttpServletResponse response) {
        EasybuyResult result = loginService.logout(token,request, response);
        return JsonUtils.object2json(result);
    }

    /**
     * 查询数据是否可用
     * @return
     */
    @RequestMapping(value="/user/check.do", method= RequestMethod.POST)
    @ResponseBody
    public String check(TbUser user) {
        List<TbUser> users= loginService.check(user);
        if (users!=null&&users.size()>0){
            return JsonUtils.object2json(EasybuyResult.ok("false")); //不可用
        }else{
            return JsonUtils.object2json(EasybuyResult.ok("true"));//可用
        }

    }

    @RequestMapping("/user/register")
    public String register(TbUser user) {
        EasybuyResult result=loginService.saveNewUser(user);
        return "login";
    }



    @RequestMapping("/user/page/login")
    public String showRegister(String redirect, Model model) {
        //把url参数传递到jsp
        model.addAttribute("redirect", redirect);
        return "login";
    }


}
