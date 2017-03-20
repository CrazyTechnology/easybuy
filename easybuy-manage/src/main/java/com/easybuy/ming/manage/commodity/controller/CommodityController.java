package com.easybuy.ming.manage.commodity.controller;

import com.easybuy.ming.manage.commodity.service.CommodityService;
import com.easybuy.ming.pojo.DatatablesPager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by ming on 2017/3/20.
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController {
    private Logger logger = Logger.getLogger(CommodityController.class);
    @Resource
    private CommodityService commodityService;

    @RequestMapping("/toList.do")
    public ModelAndView toCommodityPage(){
        return new ModelAndView("/commodity/commodityList");
    }

    @RequestMapping("/list.do")
    public DatatablesPager getCommodityList(DatatablesPager datatablesPager){
        logger.info("进入查询商品列表的操作");
      return   commodityService.selectCommodityList(datatablesPager);
    }
}
