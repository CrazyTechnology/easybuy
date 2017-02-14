package com.easybuy.ming.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2016/11/20.
 */
public interface CommonMapper {

    //查询省列表
    List<Map<String,Object>> getProvince(@Param(value="search")String search, @Param(value="page")String page);

    String getDeptTree();

    /**
     * 查询部门列表
     *
     * @param search
     * @param page
     * @return
     */
    List<Map<String, Object>> selectDeptList(@Param(value = "search") String search, @Param(value = "page") String page);

    /**
     * 查询所有的角色列表
     *
     * @param search
     * @param page
     * @return
     */
    List<Map<String, Object>> selectRoleList(@Param(value = "search") String search, @Param(value = "page") String page);
}
