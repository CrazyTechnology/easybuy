package com.easybuy.ming.manage.utils.service.impl;

import com.easybuy.ming.manage.utils.service.AreaService;
import com.easybuy.ming.mapper.CommonMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2016/11/20.
 */
@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    @Resource
    private CommonMapper commonMapper;

    /**
     * 查询所有的省份
     *
     * @param search
     * @param page
     * @return
     */
    public List<Map<String, Object>> getProvince(String search, String page) {
        return commonMapper.getProvince(search, page);
    }
}
