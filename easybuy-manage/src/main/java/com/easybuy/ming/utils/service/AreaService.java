package com.easybuy.ming.utils.service;

import java.util.List;
import java.util.Map;

/**
 * Created by ming on 2016/11/20.
 */
public interface AreaService {
    List<Map<String,Object>> getProvince(String search, String page);
}
