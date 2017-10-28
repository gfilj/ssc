package com.project.common.exception;

/**
 * Created by goforit on 2017/10/23.
 */
public class BusinessException extends ServiceException {

    public BusinessException(ExceptionEnum exceptionEnum, String message, Exception e) {
        super(exceptionEnum,message,e);
    }

    public BusinessException(ExceptionEnum exceptionEnum, Object info) {
        super(exceptionEnum, info);
    }
}
