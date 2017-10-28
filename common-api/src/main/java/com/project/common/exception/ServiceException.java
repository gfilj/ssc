package com.project.common.exception;

import com.project.common.result.Result;
import com.project.common.result.ResultBuilder;


/**
 * Created by goforit on 2017/10/22.
 */
public class ServiceException extends Throwable {

    private Result result = new Result();

    public ServiceException(ExceptionEnum exceptionEnum, Object info) {
        super(exceptionEnum.getDescription());
        ResultBuilder.getInstance(result).build(exceptionEnum, info);
    }

    public ServiceException(ExceptionEnum exceptionEnum, Object info, Throwable cause) {
        super(exceptionEnum.getDescription(), cause);
        ResultBuilder.getInstance(result).build(exceptionEnum, info);
    }

    public Result getResult() {
        return result;
    }
}

