package com.easybuy.ming.cart.interceptor;

import com.easybuy.ming.pojo.TbUser;
import com.easybuy.ming.utils.CookieUtils;
import com.easybuy.ming.utils.HttpClientUtil;
import com.easybuy.ming.utils.JsonUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-03-07.
 */
public class CartInterceptor implements HandlerInterceptor {
    private static String EASYBUY_TOKEN="EASYBUY_TOKEN";
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从cookie中获token ，然后调用redis服务查询用户信息。
        String key = CookieUtils.getCookieValue(request,EASYBUY_TOKEN);
        if(!StringUtils.isEmpty(key)){
            Map<String,String> params=new HashMap(); //传入token
            params.put("token",key);
            String value = HttpClientUtil.doPost("http://localhost:8083/sso/getLoginInfo.do",params); //调用服务
            if(!StringUtils.isEmpty(value)){
                TbUser tbUser = JsonUtils.jsonToPojo(value, TbUser.class);
                request.getSession().setAttribute("loginUser",tbUser); //将用户信息放入session中
            }
            return true;
        }
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
