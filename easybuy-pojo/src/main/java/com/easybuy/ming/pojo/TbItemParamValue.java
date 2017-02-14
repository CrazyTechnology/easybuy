package com.easybuy.ming.pojo;

public class TbItemParamValue extends TbItemParamValueKey {
    private String paramValue;

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }
}