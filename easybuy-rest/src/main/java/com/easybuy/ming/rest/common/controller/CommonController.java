package com.easybuy.ming.rest.common.controller;

import com.easybuy.ming.pojo.TbProvince;
import com.easybuy.ming.rest.common.service.CommonService;
import com.easybuy.ming.utils.EasybuyResult;
import com.easybuy.ming.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017-02-16.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Resource
    private CommonService commonService;

    @RequestMapping(value = "/getCity", produces= MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getCity(String callback){
       List<TbProvince> provinces=commonService.getCity();
        String province = JsonUtils.list2json(provinces);
    //判断是否是jsonp调用
        if (StringUtils.isBlank(callback)) {
            return province;
        }
        return callback + "(" + province + ");";
    }
}
