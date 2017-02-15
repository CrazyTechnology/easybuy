package com.easybuy.ming.manage.system.controller;

import com.easybuy.ming.pojo.TbDept;
import com.easybuy.ming.manage.system.service.DepartmentService;
import com.easybuy.ming.pojo.DatatablesPager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by ming on 2016/11/27.部门管理.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {


    private Logger logger = Logger.getLogger(DepartmentController.class);

    @Resource
    private DepartmentService departmentService;

    //跳转到部门列表
    @RequestMapping("/toList.do")
public ModelAndView toDepartmentList(){
    return new ModelAndView("system/department/departList");
}

    //显示部门列表
    @RequestMapping("/list.do")
    @ResponseBody
    public DatatablesPager getUserList(DatatablesPager datatablesPager){
        logger.info("下一步查询部门列表");
        return  departmentService.selectDepartmentList(datatablesPager);
    }


    //添加新部门
    @ResponseBody
    @RequestMapping("/saveDept.do")
    public Integer saveDepartment(TbDept dept) {
        return 0;
    }

    @RequestMapping("/toDetail.do")
    public ModelAndView selectDeptDetail(String id, ModelMap map) {
        TbDept dept = departmentService.selectDepartDetail(id);
        map.addAttribute("dept", dept);
        return new ModelAndView("system/department/departDetail", map);
    }

}
