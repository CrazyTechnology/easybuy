package com.easybuy.ming.order.service;

import com.easybuy.ming.pojo.TbOrder;

/**
 * Created by Administrator on 2017-03-20.
 */
public interface OrderService {
    TbOrder selectOrderById(String order);
}
