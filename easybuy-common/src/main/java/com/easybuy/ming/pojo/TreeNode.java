package com.easybuy.ming.pojo;

import java.io.Serializable;

public class TreeNode implements Serializable {

    private String id;
    private String text;
    private String state;

    public TreeNode(String id, String text, String state) {
        this.id = id;
        this.text = text;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
