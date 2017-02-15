package com.easybuy.ming.manage.utils.service.impl;

import com.easybuy.ming.manage.utils.service.CommonService;
import com.easybuy.ming.mapper.CommonMapper;
import com.easybuy.ming.mapper.TbAuthMapper;
import com.easybuy.ming.mapper.TbDeptMapper;
import com.easybuy.ming.pojo.TbAuth;
import com.easybuy.ming.pojo.TbAuthExample;
import com.easybuy.ming.pojo.TbDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-29.
 */
@Service
@Transactional
public class CommonServiceImpl implements CommonService {

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private TbDeptMapper deptMapper;
    @Autowired
    private TbAuthMapper authMapper;
    /**
     * 查找部门列表
     *
     * @return
     */
    public String findDeptTree() {
        String json = selectDeptTree(0);
        return json;
    }

    /**
     * 查询所有的部门列表
     *
     * @param search 查询条件
     * @param page
     * @return
     */
    public List<Map<String, Object>> getDeptList(String search, String page) {
        List<Map<String, Object>> deptList = commonMapper.selectDeptList(search, page);
        return deptList;
    }

    /**
     * 查询所有的角色
     *
     * @param search 查询条件
     * @param page
     * @return
     */
    public List<Map<String, Object>> getRoleList(String search, String page) {
        List<Map<String, Object>> roleList = commonMapper.selectRoleList(search, page);
        return roleList;
    }


    /**
     * 查询树形菜单
     *
     * @param parentId
     * @param type
     * @return
     */
    public String getMenuTree(Integer parentId, Integer type) {
        String json = getJson(parentId, type, true);
        return json;
    }

    //遍历树形菜单，及其子菜单
    public String getJson(Integer parentId, Integer type, boolean opened) {
        String json = "";
        TbAuthExample authExample = new TbAuthExample();
        TbAuthExample.Criteria criteria = authExample.createCriteria();
        List<TbAuth> list = authMapper.selectByParentId(type, parentId);
        // 根据父节点id获取所有子节点
        TbAuth auth;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                // organization=(Organization)list.get(i);
                auth = (TbAuth) list.get(i);
                List<TbAuth> list2 = authMapper.selectByParentId(type, parentId);// getOrgListByPid(organization.getgetId());
                if (list2.size() > 0) {
                    json += "{\"id\":\"" + auth.getId()
                            + "\",\"text\":\"" + auth.getName()
                            + "\",\"state\":{\"opened\":" + opened + "},";
                    json += "\"children\":[";
                    json += getJson(auth.getId(), type, false);
                    json += "]";
                    json += "}";
                } else {
                    json += "{\"id\":\"" + auth.getId() + "\",\"text\":\"" + auth.getName() + "\",\"state\":{\"opened\":false},";
                }
                if (i < list.size() - 1) {
                    json += ",";
                }
            }
        }
        return json;

    }

    //遍历查询所有部门节点以及子节点
    public String selectDeptTree(Integer parentId) {
        String json = "";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("parentId", parentId);
        List<TbDept> tbDeptsList = deptMapper.selectByExample(params);
        if (tbDeptsList != null && tbDeptsList.size() > 0) {
            for (int i = 0; i < tbDeptsList.size(); i++) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("parentId", tbDeptsList.get(i).getParentId());
                List<TbDept> tbDepts = deptMapper.selectByExample(param);
                if (tbDepts.size() > 0) {
                    json += "{\"id\":\"" + tbDeptsList.get(i).getId()
                            + "\",\"text\":\"" + tbDeptsList.get(i).getName()
                            + "\",\"state\":{\"opened\":true},";
                    json += "\"children\":[";
                    json += selectDeptTree(tbDeptsList.get(i).getId());
                    json += "]";
                    json += "}";
                } else {
                    json += "{\"id\":\"" + tbDeptsList.get(i).getId() + "\",\"text\":\"" + tbDeptsList.get(i).getName() + "\",\"state\":{\"opened\":true},";
                }
                if (i < tbDeptsList.size() - 1) {
                    json += ",";
                }

            }

        }
        return json;
    }

}
