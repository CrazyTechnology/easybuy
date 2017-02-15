package com.easybuy.ming.manage.utils.controller;

import com.easybuy.ming.manage.utils.MyRealm;
import com.easybuy.ming.utils.EasybuyResult;
import com.easybuy.ming.utils.JsonUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ming on 2016/11/20.
 */
@Controller
public class LoginController {
    Logger logger= Logger.getLogger(LoginController.class);

    //页面跳转到首页
    @RequiresAuthentication
    @RequestMapping("/index.do")
    public ModelAndView toIndex(){
        logger.info("下一步跳转到首页。。。");
        return new ModelAndView("index");
    }

    @RequestMapping("/toLogin.do")
    public ModelAndView toLoginIn(){
        logger.info("下一步跳转到登录页面。。。");
        return new ModelAndView("login");
    }




    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request) {
        logger.info("进入登录方法。。。");
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("loginPassword");
        ModelAndView modelAndView = new ModelAndView();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        token.setRememberMe(true);
        // 获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        EasybuyResult result=new EasybuyResult();
        try {
            currentUser.login(token);
            result.setData("登录成功");
            result.setMsg("index.do");
            result.setStatus(200);

            RealmSecurityManager rsm=(RealmSecurityManager)SecurityUtils.getSecurityManager();
            MyRealm rm = (MyRealm)rsm.getRealms().iterator().next();
            rm.clearAuthz();
        }catch (UnknownAccountException uae) {
//			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            request.setAttribute("message_login", "未知账户");

        } catch (IncorrectCredentialsException ice) {
//			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            request.setAttribute("message_login", "密码不正确");

        } catch (LockedAccountException lae) {
//			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            request.setAttribute("message_login", "账户已锁定");

        } catch (ExcessiveAttemptsException eae) {
//			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            request.setAttribute("message_login", "用户名或密码错误次数过多");

        }
        return JsonUtils.objectsToJson(result);

    }


    @RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("login");
    }

}
