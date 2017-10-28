package com.netease.fileupload.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by goforit on 2017/9/6.
 */
public class LogUtil {

    public static Logger getLogger(Class sample){
        return LoggerFactory.getLogger(sample);
    }
}
