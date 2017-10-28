package com.netease.fileupload.util;

import com.netease.common.privilege.client.base.util.PropertiesUtil;

import java.util.Properties;

/**
 * Created by goforit on 2017/9/19.
 */
public class PropertyLoad {

    private static Properties pro = null;
    static{
        if(pro == null){
            pro = PropertiesUtil.loadProperties("auth.properties");
        }
    }

    public static String getProperty(String name){
        return pro.getProperty(name);
    }
}
