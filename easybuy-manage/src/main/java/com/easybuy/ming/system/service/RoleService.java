package com.easybuy.ming.system.service;

import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.pojo.TbRoleAuth;

/**
 * Created by Administrator on 2017-01-18.
 */
public interface RoleService {
    /**
     * 角色列表
     *
     * @param datatablesPager
     * @return
     */
    DatatablesPager selectRoleList(DatatablesPager datatablesPager);

    /**
     * 保存分配的权限
     *
     * @param roleAuth
     * @return
     */
    Integer saveAuth(TbRoleAuth roleAuth);
}
