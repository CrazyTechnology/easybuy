package com.easybuy.ming.pojo;

import java.io.Serializable;

/**
 * Created by ming on 2016/10/26.
 */
public class PictureResult implements Serializable {

    private String error;
    private String url;
    private String message;
    public String getUrl() {
        return url;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}

