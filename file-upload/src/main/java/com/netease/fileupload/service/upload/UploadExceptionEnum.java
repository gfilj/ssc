package com.netease.fileupload.service.upload;

import com.netease.fileupload.model.Result;
import com.netease.fileupload.model.builder.ResultBuilder;

/**
 * Created by goforit on 2017/9/11.
 */
public enum UploadExceptionEnum {
    IOEXCEPTION_CAUSE(5001, "IO Exception"),
    FILE_NOT_FOUND_CAUSE(5002, "Could not find file"),
    NO_ALGORITHM_CAUSE(5003, "No Such Algorithm Exception"),
    CHECK_ERROR_CAUSE(5004, "md5 校验失败"),
    SUCCESS(200, "上传成功"),
    COMMON_CAUSE(ResultBuilder.getInstance().build());

    private Result result;

    UploadExceptionEnum(int type, String description) {
        result = ResultBuilder.getInstance().setStatus(type).setInfo(description).build();
    }

    UploadExceptionEnum(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public UploadExceptionEnum setResult(Result result) {
        this.result = result;
        return this;
    }
}
