package com.netease.fileupload.service.upload;

import com.netease.fileupload.model.Result;

/**
 * Created by goforit on 2017/9/11.
 */
public class UploadException extends Throwable {

    private UploadExceptionEnum uploadExceptionEnum;

    public UploadException(UploadExceptionEnum uploadExceptionEnum) {
        super(uploadExceptionEnum.getResult().getMsg());
        this.uploadExceptionEnum = uploadExceptionEnum;
    }

    public UploadException(UploadExceptionEnum uploadExceptionEnum, Throwable cause) {

        super(uploadExceptionEnum.getResult().getMsg(), cause);
        this.uploadExceptionEnum = uploadExceptionEnum;
    }

    public UploadException(Result result, Throwable cause) {
        this(UploadExceptionEnum.COMMON_CAUSE.setResult(result),cause);
    }
    public UploadException(Result result) {
        this(UploadExceptionEnum.COMMON_CAUSE.setResult(result));
    }

    public UploadExceptionEnum getUploadExceptionEnum() {
        return uploadExceptionEnum;
    }
}
