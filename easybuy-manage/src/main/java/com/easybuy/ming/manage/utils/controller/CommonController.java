package com.easybuy.ming.manage.utils.controller;

import com.easybuy.ming.manage.utils.service.CommonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016-12-29.
 */
@RequestMapping("/common")
@Controller
public class CommonController {

    @Resource
    private CommonService commonService;


    /**
     * 查找部门列表，树形结构
     *
     * @return
     */
    @RequestMapping("/getDeptTree.do")
    @ResponseBody
    public void getDeptTree(HttpServletResponse response) {
        String json = commonService.findDeptTree();
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找菜单列表，树形结构
     *
     * @return
     */
    @RequestMapping("/getMenuTree.do")
    @ResponseBody
    public void getMenuTree(HttpServletResponse response, Integer parentId, Integer type) {
        String json = commonService.getMenuTree(parentId, type);
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询所有部门列表
     *
     * @param search
     * @param page
     * @return
     */
    @RequestMapping("/getDeptList.do")
    @ResponseBody
    public Map<String, Object> getDeptList(String search, String page) {
        List<Map<String, Object>> select = commonService.getDeptList(search, page);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result", select);
        result.put("total", select.size());
        return result;
    }


    /**
     * 查询所有角色
     *
     * @param search
     * @param page
     * @return
     */
    @RequestMapping("/getRoleList.do")
    @ResponseBody
    public Map<String, Object> getRoleList(String search, String page) {
        List<Map<String, Object>> select = commonService.getRoleList(search, page);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result", select);
        result.put("total", select.size());
        return result;
    }




}
