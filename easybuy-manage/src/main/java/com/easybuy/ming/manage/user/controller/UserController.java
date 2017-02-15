package com.easybuy.ming.manage.user.controller;

import com.easybuy.ming.manage.user.service.UserService;
import com.easybuy.ming.pojo.TbUser;
import com.easybuy.ming.pojo.DatatablesPager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ming on 2016/11/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger= Logger.getLogger(UserController.class);
    @Resource
    private UserService userServiceImpl;

    //页面跳转到会员列表
    @RequestMapping("/toUserPage.do")
    @ResponseBody
    public ModelAndView toUserPage(){
        logger.info("下一步跳转到会员列表页面");
        return new ModelAndView("user/userList");

    }

    //页面跳转到会员新增
    @RequestMapping("/toUserAddPage.do")
    @ResponseBody
    public ModelAndView toUserAddPage(){
        logger.info("下一步跳转到会员新增页面");
        return new ModelAndView("user/userAdd");

    }

    //显示会员列表
    @RequestMapping("/list.do")
    @ResponseBody
    public DatatablesPager getUserList(DatatablesPager datatablesPager){
        logger.info("下一步查询订单列表");
        return  userServiceImpl.selectUserList(datatablesPager);
    }

    //根据用户id查询用户详细信息
    @RequestMapping("/toDetail.do")
    public ModelAndView getUserDetail(String id){
        logger.info("根据id查询用户详细信息");
        TbUser user= userServiceImpl.selectUserDetail(id);
        ModelMap modelMap=new ModelMap();
        modelMap.addAttribute("user",user);
        return new ModelAndView("/user/userDetail",modelMap);
    }

    /**
     * @param user    用户信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/addUser.do",method = RequestMethod.POST)
    @ResponseBody
    public Integer addUser(TbUser user, HttpServletRequest request) {
        Integer result = userServiceImpl.addUser(user);
        return result;
    }

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return 返回操作结果
     */
    @RequestMapping(value = "/deleteUser.do", method = RequestMethod.POST)
    @ResponseBody
    public Integer deleteUser(String id, HttpServletRequest request) {
        Integer result = userServiceImpl.deleteUser(id);
        return result;
    }


}
