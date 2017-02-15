package com.easybuy.ming.manage.system.service;

import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.pojo.TbAuth;

/**
 * Created by Administrator on 2017-01-10.
 */
public interface MenuService {

    /**
     * 查询菜单列表
     *
     * @param datatablesPager
     * @return
     */
    DatatablesPager selectMenuList(DatatablesPager datatablesPager);

    /**
     * 保存菜单
     *
     * @param auth
     * @return
     */
    Integer saveMenu(TbAuth auth);

    /**
     * 编辑菜单
     *
     * @param auth
     * @return
     */
    Integer editMenu(TbAuth auth);

    /**
     * 根据登录账户查询菜单
     *
     * @param pid
     * @param type
     * @param loginName
     * @return
     */
    String findMenuJson(Integer pid, Integer type, String loginName);
}
