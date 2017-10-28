package com.netease.fileupload.service.MD5;

/**
 * Created by goforit on 2017/9/11.
 */
public class MD5Exception extends Throwable {

    private MD5ExceptionEnum md5ExceptionEnum;

    public MD5Exception(MD5ExceptionEnum md5ExceptionEnum) {
        super(md5ExceptionEnum.getResult().getMsg());
        this.md5ExceptionEnum = md5ExceptionEnum;
    }

    public MD5Exception(MD5ExceptionEnum md5ExceptionEnum, Throwable cause) {

        super(md5ExceptionEnum.getResult().getMsg(), cause);
        this.md5ExceptionEnum = md5ExceptionEnum;
    }

    public MD5ExceptionEnum getMd5ExceptionEnum() {
        return md5ExceptionEnum;
    }

}
