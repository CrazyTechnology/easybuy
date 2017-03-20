package com.easybuy.ming.manage.commodity.service.impl;

import com.easybuy.ming.manage.commodity.service.CommodityService;
import com.easybuy.ming.mapper.TbItemMapper;
import com.easybuy.ming.pojo.DatatablesPager;
import com.easybuy.ming.pojo.TbItem;
import com.easybuy.ming.pojo.TbItemExample;
import com.easybuy.ming.utils.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2017/3/20.
 */
@Transactional
@Service
public class CommodityServiceImpl implements CommodityService {

    private TbItemMapper tbItemMapper;

    //查询商品列表
    public DatatablesPager selectCommodityList(DatatablesPager datatablesPager) {
        PageHelper.startPage(datatablesPager);
        List<TbItem> userList = tbItemMapper.selectByExample(new TbItemExample());
        return PageHelper.endPage(userList);
    }
}
