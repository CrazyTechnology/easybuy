package com.easybuy.ming.manage.system.service.impl;

import com.easybuy.ming.manage.system.service.MenuService;
import com.easybuy.ming.mapper.TbAuthMapper;
import com.easybuy.ming.pojo.*;
import com.easybuy.ming.utils.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-01-10.
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Resource
    private TbAuthMapper tbAuthMapper;

    /**
     * @param datatablesPager
     * @return
     */
    public DatatablesPager selectMenuList(DatatablesPager datatablesPager) {
        PageHelper.startPage(datatablesPager);
        Map<String, Object> params = datatablesPager.getParams();
        List<TbAuth> userList = tbAuthMapper.selectByExample(params);
        return PageHelper.endPage(userList);
    }

    /**
     * 保存菜单
     *
     * @param auth
     * @return
     */
    public Integer saveMenu(TbAuth auth) {
        return tbAuthMapper.insertSelective(auth);
    }

    /**
     * 编辑菜单
     *
     * @param auth
     * @return
     */
    public Integer editMenu(TbAuth auth) {
        return tbAuthMapper.updateByPrimaryKeySelective(auth);
    }


    /**
     * 根据用户查询权限信息
     *
     * @param pid       上级菜单
     * @param type      类型
     * @param loginName 登录名
     * @return
     */
    public String findMenuJson(Integer pid, Integer type, String loginName) {
        String json = getMenuJson(pid, type, loginName, true);
        return json;
    }

    private String getMenuJson(Integer pid, Integer type, String loginName, boolean open) {
        String json = "";
        List<Map<String, Object>> list = tbAuthMapper.findMenuJson(type, pid, loginName);
        // 根据父节点id获取所有子节点
        TbAuth tdSysAuth;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                // organization=(Organization)list.get(i);
                tdSysAuth = (TbAuth) list.get(i);
                Integer openway = tdSysAuth.getOpenway();
                List<TbAuth> list2 = tbAuthMapper.selectByParentId(type, tdSysAuth.getId());// getOrgListByPid(organization.getgetId());
                if (list2.size() > 0) {
                    if (open) {
                        json += "<li class=\"nav-item open\"><a href=\"javascript:;\" class=\"nav-link nav-toggle\" style=\"border-top: 0px;\"> <span class=\"title\">" + tdSysAuth.getName() + "</span><span class=\"arrow open\"></span></a>";
                        json += "<ul class=\"sub-menu page-sidebar-menu\" style=\"display: block;margin-top: 0px;\">";
                    } else {
                        json += "<li class=\"nav-item\"><a href=\"javascript:;\" class=\"nav-link nav-toggle\" style=\"border-top: 0px;\">  <span class=\"title\">" + tdSysAuth.getName() + "</span><span class=\"arrow\"></span></a>";
                        json += "<ul class=\"sub-menu page-sidebar-menu\" style=\"margin-top: 0px;\">";
                    }
                    json += getMenuJson(tdSysAuth.getId(), type, loginName, false);
                    json += "</ul>";
                    json += "</li>";
                } else {
                    if (openway == 1) {
                        json += "<li class=\"nav-item\"><a href=\"" + tdSysAuth.getUrl() + "\" class=\"nav-link ajaxify\" style=\"border-top: 0px;\">  <span class=\"title\">" + tdSysAuth.getName() + "</span> <span class=\"selected\"></span></a></li>";
                    } else if (openway == 0) {
                        json += "<li class=\"nav-item\"><a href=\"" + tdSysAuth.getUrl() + "\" target=\"_blank\" class=\"nav-link \" style=\"border-top: 0px;\"> <span class=\"title\">" + tdSysAuth.getName() + "</span> <span class=\"selected\"></span></a></li>";
                    } else {
                        json += "<li class=\"nav-item\"><a href=\"" + tdSysAuth.getUrl() + "\" class=\"nav-link ajaxify\" style=\"border-top: 0px;\">  <span class=\"title\">" + tdSysAuth.getName() + "</span> <span class=\"selected\"></span></a></li>";
                    }
                }
                /*
				 * if (i < list.size() - 1) { //json += ","; }
				 */
            }
        }
        return json;
    }
}
