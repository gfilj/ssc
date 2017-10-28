package com.netease.fileupload.util;

/**
 * Created by goforit on 2017/8/25.
 */
public class Constant {
    public static final String LOG_ERROR_FORMAT="\n\n*******************%s*******************\n";
    public static final String LOG_INFO_FORMAT="\n\n+++++++++++++++++++%s+++++++++++++++++++\n";

    public static final int HTTP_STATUS_OK = 200;
    public static final String HTTP_INFO_OK = "success";
    public static final int HTTP_STATUS_ERROR = 500;
    public static final String HTTP_INFO_ERROR = "error";

    public static String INFOStr(String info){
        return String.format(LOG_INFO_FORMAT,info);
    }
    public static String ERRORStr(String error){
        return String.format(LOG_ERROR_FORMAT,error);
    }
}
