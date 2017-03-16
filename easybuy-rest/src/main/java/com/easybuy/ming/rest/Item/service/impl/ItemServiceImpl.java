package com.easybuy.ming.rest.Item.service.impl;

import com.easybuy.ming.mapper.TbItemCatMapper;
import com.easybuy.ming.pojo.TbItemCat;
import com.easybuy.ming.pojo.TbItemCatExample;
import com.easybuy.ming.rest.Item.service.ItemService;
import com.easybuy.ming.rest.redis.service.JedisClient;
import com.easybuy.ming.rest.util.pojo.ItemCatNode;
import com.easybuy.ming.rest.util.pojo.ItemCatResult;
import com.easybuy.ming.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-02-16.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Resource
    private JedisClient jedisClient;

    @Value("${REDIS_EASYBUY_CAT_KEY=EASYBUY_CAT}")
    private String REDIS_EASYBUY_CAT_KEY;

    public ItemCatResult getItemCatList() {
        String cat="cat"; //目录
        //添加缓存
        //查询数据库之前先查询缓存，如果有直接返回
        try {
            //从redis中取缓存数据
            String json = jedisClient.hget(REDIS_EASYBUY_CAT_KEY,"cat");
            if (!StringUtils.isBlank(json)) {
                //把json转换成List

               // return provinces;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //调用递归方法查询商品分类列表
        List catList = getItemCatList(0l);
        //返回结果
        ItemCatResult result = new ItemCatResult();
        result.setData(catList);
        return result;
    }

    private List getItemCatList(Long parentId) {
        //根据parentId查询列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List resultList = new ArrayList();
        for (TbItemCat tbItemCat : list) {
            //如果是父节点
            if (tbItemCat.getIsParent()) {
                ItemCatNode node = new ItemCatNode();
                node.setUrl("/products/"+tbItemCat.getId()+".html");
                //如果当前节点为第一级节点
                if (tbItemCat.getParentId() == 0) {
                    node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
                } else {
                    node.setName(tbItemCat.getName());
                }
                node.setItems(getItemCatList(tbItemCat.getId()));
                //把node添加到列表
                resultList.add(node);
            } else {
                //如果是叶子节点
                String item = "|" + tbItemCat.getName();
                resultList.add(item);
            }
        }
        return resultList;
    }


}
