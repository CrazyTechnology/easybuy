package com.easybuy.ming.rest.common.service.impl;

import com.easybuy.ming.mapper.TbProvinceMapper;
import com.easybuy.ming.pojo.TbProvince;
import com.easybuy.ming.pojo.TbProvinceExample;
import com.easybuy.ming.rest.common.service.CommonService;
import com.easybuy.ming.rest.redis.service.JedisClient;
import com.easybuy.ming.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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

    @Resource
    private JedisClient jedisClient;
    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;

    /**
     * 查询所有省份列表
     * @return
     */
    public List<TbProvince> getCity() {
        String city="city";
        //添加缓存
        //查询数据库之前先查询缓存，如果有直接返回
        try {
            //从redis中取缓存数据
            String json = jedisClient.hget(REDIS_CONTENT_KEY, city+"");
            if (!StringUtils.isBlank(json)) {
                //把json转换成List
                List<TbProvince> provinces = JsonUtils.jsonToList(json, TbProvince.class);
                return provinces;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbProvinceExample example=new TbProvinceExample();
        List<TbProvince> provinces = tbProvinceMapper.selectByExample(example);
        //返回结果之前，向缓存中添加数据
        try {
            //为了规范key可以使用hash
            //定义一个保存内容的key，hash中每个项就是cid
            //value是list，需要把list转换成json数据。
            jedisClient.hset(REDIS_CONTENT_KEY, city+"", JsonUtils.objectsToJson(provinces));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  provinces;
    }
}
