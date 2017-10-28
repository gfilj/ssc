package com.project.common.exception;

/**
 * Created by goforit on 2017/10/23.
 */
public class ArgumentException extends ServiceException{

    public ArgumentException(ExceptionEnum exceptionEnum, Object info) {
        super(exceptionEnum, info);
    }

    public ArgumentException(ExceptionEnum exceptionEnum, Object info, Throwable cause) {
        super(exceptionEnum, info, cause);
    }
}
