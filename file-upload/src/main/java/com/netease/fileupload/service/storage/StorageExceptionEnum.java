package com.netease.fileupload.service.storage;

import com.netease.fileupload.model.Result;
import com.netease.fileupload.model.builder.ResultBuilder;

/**
 * Created by goforit on 2017/9/8.
 */
public enum StorageExceptionEnum {
    EMPTY_CAUSE(4001, "Failed to store empty file %s"),
    IOEXCEPTION_CAUSE(4002, "Failed to store file %s"),
    SECURITY_CAUSE(4003, "Cannot store file with relative path outside current directory %s"),
    READ_CAUSE(4004, "Failed to read stored files"),
    FILE_NOT_FOUND_CAUSE(4005, "Could not find file: %s"),

    INIT_CAUSE(4006,"Could not initialize storage");

    private Result result;

    StorageExceptionEnum(int type, String description) {
        result = ResultBuilder.getInstance().setStatus(type).setInfo(description).build();
    }

    public Result getResult() {
        return result;
    }

    public StorageExceptionEnum setFilename(String filename) {
        result.setMsg(String.format(result.getMsg(),filename));
        return this;
    }

}
