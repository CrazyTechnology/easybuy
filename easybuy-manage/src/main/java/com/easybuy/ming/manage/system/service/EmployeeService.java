package com.easybuy.ming.manage.system.service;

import com.easybuy.ming.pojo.TbDeptUser;
import com.easybuy.ming.pojo.DatatablesPager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2016/12/4.
 */
public interface EmployeeService {
    //根据登录名查找用户
    TbDeptUser findUserByName(String username);

    //根据用户名查询用户全权限
    List<Map<String,Object>> findAuthByLoginName(@Param("loginName") String loginName);

    /**
     * 查询部门用户列表
     *
     * @param datatablesPager
     * @return 用户列表
     */
    DatatablesPager selectDepartUserList(DatatablesPager datatablesPager);

    /**
     * 删除部门用户
     *
     * @param id
     * @return
     */
    int deleteDeptUser(String id);

    /**
     * 添加部门用户
     *
     * @param user
     * @return
     */
    int saveDeptUser(TbDeptUser user);
}
