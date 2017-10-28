package com.netease.fileupload.service.organ;

import com.netease.fileupload.model.Result;
import com.netease.fileupload.model.builder.ResultBuilder;

/**
 * Created by goforit on 2017/9/11.
 */
public enum OrganizationExceptionEnum {
    IOEXCEPTION_CAUSE(5001, "IO Exception");

    private Result result;

    OrganizationExceptionEnum(int type, String description) {
        result = ResultBuilder.getInstance().setStatus(type).setInfo(description).build();
    }

    public Result getResult() {
        return result;
    }

}
