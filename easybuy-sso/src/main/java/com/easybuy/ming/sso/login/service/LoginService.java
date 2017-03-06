package com.easybuy.ming.sso.login.service;

import com.easybuy.ming.utils.EasybuyResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017-03-03.
 */
public interface LoginService {
    EasybuyResult login(String username, String password, HttpServletRequest request, HttpServletResponse response);

    EasybuyResult getLoginInfo(String token, HttpServletRequest request, HttpServletResponse response);
}
