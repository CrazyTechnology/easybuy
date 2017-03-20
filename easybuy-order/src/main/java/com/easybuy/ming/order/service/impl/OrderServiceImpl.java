package com.easybuy.ming.order.service.impl;

import com.easybuy.ming.mapper.TbOrderMapper;
import com.easybuy.ming.order.service.OrderService;
import com.easybuy.ming.pojo.TbOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017-03-20.
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TbOrderMapper tbOrderMapper;

    /**
     * 查询订单详情
     * @param order 订单id
     * @return
     */
    public TbOrder selectOrderById(String order) {
        TbOrder tbOrder = tbOrderMapper.selectByPrimaryKey(order);
        return tbOrder;
    }
}
