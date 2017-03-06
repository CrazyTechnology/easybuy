package com.easybuy.ming.portal.utils.controller;

import com.easybuy.ming.pojo.TbUser;
import com.easybuy.ming.utils.CookieUtils;
import com.easybuy.ming.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017-02-05.
 */
@Controller
public class PageController {

    Logger logger = Logger.getLogger(PageController.class);


    //页面跳转到首页
    @RequestMapping("/index.do")
    public ModelAndView toIndex(HttpServletRequest request) {
        logger.info("下一步跳转到首页。。。");
        return new ModelAndView("index");
    }

    //页面跳转到购物车
    @RequestMapping("/shoppingCart.do")
    public ModelAndView toShoppingCart() {
        logger.info("下一步跳转到购物车。。。");
        return new ModelAndView("shop");
    }

}
