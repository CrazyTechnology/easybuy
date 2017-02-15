package com.easybuy.ming.system.controller;

import com.easybuy.ming.pojo.TbDeptUser;
import com.easybuy.ming.system.service.EmployeeService;
import com.easybuy.ming.pojo.DatatablesPager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by ming on 2016/12/4. 部门员工管理
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private Logger logger = Logger.getLogger(DepartmentController.class);
    @Resource
    private EmployeeService employeeService;

    //跳转到部门列表
    @RequestMapping("/toList.do")
    public ModelAndView toEmployeeList(){
        return new ModelAndView("system/department_user/userList");
    }

    //显示部门列表
    @RequestMapping("/list.do")
    @ResponseBody
    public DatatablesPager getUserList(DatatablesPager datatablesPager) {
        logger.info("下一步查询部门用户列表");
        return employeeService.selectDepartUserList(datatablesPager);
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public Integer deleteDeptUser(String id) {
        logger.info("删除用户操作");
        int result = employeeService.deleteDeptUser(id);
        return result;
    }


    /**
     * @param user 添加新用户
     * @return
     */
    @RequestMapping("/saveDeptUser.do")
    @ResponseBody
    public Integer saveDeptUser(TbDeptUser user) {
        logger.info("添加用户操作");
        int result = employeeService.saveDeptUser(user);
        return result;
    }




}
