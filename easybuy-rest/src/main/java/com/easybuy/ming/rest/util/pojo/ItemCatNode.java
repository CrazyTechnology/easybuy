package com.easybuy.ming.rest.util.pojo;

import java.util.List;

/**
 * Created by ming on 2017/3/9.
 * 商品目录节点
 */
public class ItemCatNode{
    private String name; //节点名称
    private String url; //节点路径
    private List items;

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
