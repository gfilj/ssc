package com.project.common.result;

import com.project.common.exception.ExceptionEnum;

/**
 * Created by goforit on 2017/10/22.
 */
public class ResultBuilder {

    private Result result;

    public static ResultBuilder getInstance(){
        return new ResultBuilder();
    }

    public static ResultBuilder getInstance(Result result){
        return new ResultBuilder(result);
    }

    public ResultBuilder() {
        this.result = new Result();
    }

    public ResultBuilder(Result result) {
        this.result = result;
    }

    public Result build(ExceptionEnum exceptionEnum, Object data){
        result.setData(data);
        result.setMsg(exceptionEnum.getDescription());
        result.setResultcode(exceptionEnum.getType());
        return result;
    }
}
