package com.easybuy.ming.manage.user.service;

import com.easybuy.ming.pojo.TbUser;
import com.easybuy.ming.pojo.DatatablesPager;

/**
 * Created by ming on 2016/11/20.
 */
public interface UserService {
    DatatablesPager selectUserList(DatatablesPager datatablesPager);

    //查看用户详情
    TbUser selectUserDetail(String id);

    //添加新用户
    Integer addUser(TbUser user);

    //删除用户
    Integer deleteUser(String id);
}
