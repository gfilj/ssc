package com.netease.fileupload.model.builder;

import com.netease.fileupload.model.Result;

/**
 * Created by goforit on 2017/9/6.
 */
public class ResultBuilder {

    private Result result = new Result();

    public static ResultBuilder getInstance() {
        return new ResultBuilder();
    }
    public static ResultBuilder getInstance(Result result) {
        return new ResultBuilder(result);
    }

    public ResultBuilder setInfo(String info) {
        result.setMsg( info);
        return this;
    }

    public ResultBuilder setStatus(int status) {
        result.setResultcode( status);
        return this;
    }

    public ResultBuilder setData(Object data) {
        result.setData( data);
        return this;
    }

    public ResultBuilder(Result result) {
        this.result = result;
    }

    public ResultBuilder() {
    }

    public Result build() {
        return result;
    }

}
