package com.easybuy.ming.utils.controller;

import com.easybuy.ming.utils.service.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2016/11/20.
 * 省市区级联下拉框
 */
@Controller
public class AreaController {

    @Resource
    private AreaService areaServiceImpl;

    /**
     * 查找省份
     * @param search
     * @param page
     * @return
     */
    @RequestMapping("common/getProvince.do")
    @ResponseBody
    public Map<String, Object> getProvince(String search,String page){
        List<Map<String, Object>> select= areaServiceImpl.getProvince(search,page);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("result", select);
        result.put("total", select.size());
        return result;
    }

}
