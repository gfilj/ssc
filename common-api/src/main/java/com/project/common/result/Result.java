package com.project.common.result;

import java.io.Serializable;

/**
 * Created by goforit on 2017/10/22.
 */
public class Result implements Serializable {

    private static final long serialVersionUID = -14972284664427720L;
    private int resultcode;
    private String msg;
    private Object data;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultcode=" + resultcode +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
