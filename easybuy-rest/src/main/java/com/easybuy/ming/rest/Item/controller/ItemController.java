package com.easybuy.ming.rest.Item.controller;

import com.easybuy.ming.rest.Item.service.ItemService;
import com.easybuy.ming.rest.util.pojo.ItemCatResult;
import com.easybuy.ming.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017-02-16.
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemCatService;

    @RequestMapping(value="/cat/list",produces= MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback) {
        ItemCatResult result = itemCatService.getItemCatList();
        String json = JsonUtils.object2json(result);
        if (StringUtils.isBlank(callback)) {
            //需要把result转换成字符串
            return json;
        }
        //如果字符串不为空，需要支持jsonp调用
        //需要把result转换成字符串
        return callback + "(" + json + ");";
    }

}
