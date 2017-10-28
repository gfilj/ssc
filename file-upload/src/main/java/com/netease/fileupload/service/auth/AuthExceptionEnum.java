package com.netease.fileupload.service.auth;

import com.netease.fileupload.model.Result;
import com.netease.fileupload.model.builder.ResultBuilder;

/**
 * Created by goforit on 2017/9/18.
 */
public enum  AuthExceptionEnum {
    EXECUTEREQUEST_CAUSE(6001, "executeRequest error");

    private Result result;

    AuthExceptionEnum(int type, String description) {
        result = ResultBuilder.getInstance().setStatus(type).setInfo(description).build();
    }

    public Result getResult() {
        return result;
    }
}
