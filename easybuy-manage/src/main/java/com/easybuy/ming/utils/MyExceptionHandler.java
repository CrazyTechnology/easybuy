package com.easybuy.ming.utils;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MyExceptionHandler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);
        ModelAndView modelAndView = new ModelAndView();
        ex.printStackTrace();
        if (ex instanceof UnauthorizedException) {//org.apache.shiro.authz.UnauthenticatedException: The current Subject is not authenticated.  Access denied.
            //ajax请求无权限时
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                response.setHeader("sessionstatus", "UnauthorizedException");
            }
            modelAndView.setViewName("error/extra-500");
            return modelAndView;
        }
        //用户未登录状态
        if (ex instanceof UnauthenticatedException) {
            modelAndView.setViewName("login/login");
        }
        return modelAndView;
    }

}
