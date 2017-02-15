package com.easybuy.ming.manage.system.controller;

import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.pojo.TbRoleAuth;
import com.easybuy.ming.manage.system.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017-01-18.
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private Logger logger = Logger.getLogger(RoleController.class);

    @Resource
    private RoleService roleService;

    //跳转到角色列表
    @RequestMapping("/toList.do")
    public ModelAndView toMenuList() {
        return new ModelAndView("system/role/roleList");
    }

    //显示菜单列表
    @RequestMapping("/list.do")
    @ResponseBody
    public DatatablesPager getMenuList(DatatablesPager datatablesPager) {
        logger.info("下一步------------查询角色列表");
        return roleService.selectRoleList(datatablesPager);
    }

    //保存权限
    @RequestMapping("/saveAuth.do")
    @ResponseBody
    public Integer saveAuth(TbRoleAuth roleAuth) {
        logger.info("下一步---------保存分配权限");
        return roleService.saveAuth(roleAuth);
    }
}
