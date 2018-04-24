package com.project.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by goforit on 2017/9/6.
 */
public class LogUtil {


    public static final String reg = "【%s】：%s";
    public static final String regHeader = "【%s】";

    public static Log getLogger(Class sample) {
        return LogFactory.getLog(sample);
    }

    /**
     * 传入的参数必须为基数
     * 第一个为当前功能的打印
     *
     * @param strArr
     * @return
     */
    public static String logstr(Object... strArr) {
        if (strArr.length % 2 == 1) {
            String log = String.format(regHeader, strArr[0]);
            for (int i = 1; i < strArr.length; i += 2) {
                log += String.format(reg, strArr[i], strArr[i + 1]);
            }
            return log;
        }
        return "日志不符合要求";
    }
}
