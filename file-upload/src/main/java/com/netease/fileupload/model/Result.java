package com.netease.fileupload.model;

import java.io.Serializable;

/**
 * Created by goforit on 2017/9/5.
 */
public class Result implements Serializable{

    private String msg;
    private int resultcode;
    private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
