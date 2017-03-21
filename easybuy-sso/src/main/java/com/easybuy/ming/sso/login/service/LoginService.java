package com.easybuy.ming.sso.login.service;

import com.easybuy.ming.pojo.TbUser;
import com.easybuy.ming.utils.EasybuyResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017-03-03.
 */
public interface LoginService {
    /**
     * 登录方法
     * @param username 用户名
     * @param password 密码
     * @param request
     * @param response
     * @return
     */
    EasybuyResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取登录信息
     * @param token
     * @param request
     * @param response
     * @return
     */
    EasybuyResult getLoginInfo(String token, HttpServletRequest request, HttpServletResponse response);

    /**
     * 退出登录
     * @param token 用户标示
     * @param request
     * @param response
     * @return
     */
    EasybuyResult logout(String token, HttpServletRequest request, HttpServletResponse response);

    /**
     *
     * @param user
     * @return
     */
    List<TbUser> check(TbUser user);

    /**
     * 注册新的用户
     * @param user
     * @return
     */
    EasybuyResult saveNewUser(TbUser user);
}
