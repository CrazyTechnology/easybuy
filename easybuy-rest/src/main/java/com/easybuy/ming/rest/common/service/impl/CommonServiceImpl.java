package com.easybuy.ming.rest.common.service.impl;

import com.easybuy.ming.mapper.TbProvinceMapper;
import com.easybuy.ming.pojo.TbProvince;
import com.easybuy.ming.pojo.TbProvinceExample;
import com.easybuy.ming.rest.common.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017-02-16.
 */
@Transactional
@Service
public class CommonServiceImpl implements CommonService {

    @Resource
    private TbProvinceMapper tbProvinceMapper;

    /**
     * 查询所有省份列表
     * @return
     */
    public List<TbProvince> getCity() {
        TbProvinceExample example=new TbProvinceExample();
        List<TbProvince> provinces = tbProvinceMapper.selectByExample(example);
        return provinces;
    }
}
