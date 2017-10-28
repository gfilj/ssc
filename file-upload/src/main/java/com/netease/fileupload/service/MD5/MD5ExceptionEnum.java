package com.netease.fileupload.service.MD5;

import com.netease.fileupload.model.Result;
import com.netease.fileupload.model.builder.ResultBuilder;

/**
 * Created by goforit on 2017/9/11.
 */
public enum MD5ExceptionEnum {
    IOEXCEPTION_CAUSE(5001, "IO Exception"),
    FILE_NOT_FOUND_CAUSE(5002, "Could not find file"),
    NO_ALGORITHM_CAUSE(5003, "No Such Algorithm Exception"),
    CHECK_ERROR_CAUSE(5004, "md5 校验失败");

    private Result result;

    MD5ExceptionEnum(int type, String description) {
        result = ResultBuilder.getInstance().setStatus(type).setInfo(description).build();
    }

    public Result getResult() {
        return result;
    }

}
