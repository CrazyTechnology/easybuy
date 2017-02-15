package com.easybuy.ming.manage.system.controller;

import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.pojo.TbAuth;
import com.easybuy.ming.pojo.TbDeptUser;
import com.easybuy.ming.manage.system.service.MenuService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 菜单，按钮权限控制
 * Created by Administrator on 2017-01-10.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private Logger logger = Logger.getLogger(MenuController.class);
    @Resource
    private MenuService menuService;


    //跳转到菜单列表
    @RequestMapping("/toList.do")
    public ModelAndView toMenuList() {
        return new ModelAndView("system/menu/menu");
    }

    //显示菜单列表
    @RequestMapping("/list.do")
    @ResponseBody
    public DatatablesPager getMenuList(DatatablesPager datatablesPager) {
        logger.info("下一步------------查询部门用户列表");
        return menuService.selectMenuList(datatablesPager);
    }

    //添加新的菜单
    @RequestMapping("/save.do")
    @ResponseBody
    public Integer saveMenu(TbAuth auth) {
        logger.info("下一步------------------保存菜单");
        return menuService.saveMenu(auth);
    }

    //编辑菜单内容
    @RequestMapping("/edit.do")
    @ResponseBody
    public Integer editMenu(TbAuth auth) {
        logger.info("下一步------------------保存菜单");
        return menuService.editMenu(auth);
    }


    /**
     * 查询菜单按钮
     *
     * @param session
     * @param response
     */
    @RequestMapping("/findMenuJson.do")
    @ResponseBody
    public void findMenuJson(HttpSession session, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        TbDeptUser tdSysEmp = (TbDeptUser) session.getAttribute("currentUser");
        String loginName = tdSysEmp.getLoginName();
        String json = menuService.findMenuJson(6, 1, loginName);
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
