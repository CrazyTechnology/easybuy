package com.easybuy.ming.pojo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by ming on 2016/10/13.
 */
public class DatatablesPager implements Serializable {
    // 数据
    Object data;
    // 所有数据的条数
    Integer recordsTotal;
    // 加上过滤条件后的数据条数
    Integer recordsFiltered;
    // 获取请求次数
    Integer draw;
    // 数据起始位置
    String start;
    // 数据长度
    String length;
    // 错误信息
    String error;
    //
    Integer displayLength = 10;

    //封装前台查询条件
    Map<String,Object> params;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getDisplayLength() {
        return displayLength;
    }

    public void setDisplayLength(Integer displayLength) {
        this.displayLength = displayLength;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
