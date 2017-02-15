package com.easybuy.ming.utils.service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-29.
 */
public interface CommonService {


    String selectDeptTree(Integer parentId);

    String findDeptTree();
    /**
     * 查询所有部门列表
     *
     * @param search 查询条件
     * @param page
     * @return
     */

    List<Map<String, Object>> getDeptList(String search, String page);

    /**
     * 查询所有的角色
     *
     * @param search 查询条件
     * @param page
     * @return
     */
    List<Map<String, Object>> getRoleList(String search, String page);

    /**
     * 查询菜单
     *
     * @param parentId
     * @param type
     * @return
     */
    String getMenuTree(Integer parentId, Integer type);
}
