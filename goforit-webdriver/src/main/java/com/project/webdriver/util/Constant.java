package com.project.webdriver.util;

/**
 * Created by goforit on 2017/8/25.
 */
public class Constant {
    private static final String LOG_ERROR_FORMAT="\n\n*******************%s*******************\n";
    private static final String LOG_INFO_FORMAT="\n\n+++++++++++++++++++%s+++++++++++++++++++\n";

    public static String INFOStr(String info){
        return String.format(LOG_INFO_FORMAT,info);
    }
    public static String ERRORStr(String error){
        return String.format(LOG_ERROR_FORMAT,error);
    }
}
