package com.easybuy.ming.order.controller;

import com.easybuy.ming.order.service.OrderService;
import com.easybuy.ming.pojo.TbOrder;
import com.easybuy.ming.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017-03-20.
 * 处理订单相关业务
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/detail.do")
    @ResponseBody
    public String selectOrderById(String order){
        //根据订单id查询订单详情
        TbOrder orderDetail=orderService.selectOrderById(order);
        if(orderDetail!=null){
            return JsonUtils.object2json(orderDetail);
        }
        return "";
    }
}
