package com.project.util;

import com.project.property.LotteryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import static com.project.util.Package.getRealClassName;

/**
 * Created by goforit on 2017/8/16.
 */
public class ClassloaderUtil {

    private static Logger logger = LoggerFactory.getLogger(ClassloaderUtil.class);


    public static boolean isSSCBetWin(String lotterMethod, String betStr, String winStr) {
        return isBetWin(LotteryType.SSC.getValue(), lotterMethod, betStr, winStr);
    }

    /**
     * 判断是否中奖
     *
     * @param type
     * @param lotterMethod
     * @param betStr
     * @param winStr
     * @return
     */
    public static boolean isBetWin(String type, String lotterMethod, String betStr, String winStr) {
        try {
            String className = lotterMethod.substring(0, lotterMethod.lastIndexOf("."));
            String methodStr = lotterMethod.substring(lotterMethod.lastIndexOf(".") + 1);

            Class<?> ssc = ClassLoader.getSystemClassLoader().loadClass(getClassName(className, type));
            Object owner = ssc.newInstance();
            Class[] paramterType = {String.class, String.class};
            Method method = ssc.getMethod(methodStr, paramterType);

            return (boolean) method.invoke(owner, betStr, winStr);
        } catch (Exception e) {
            logger.error(String.format("%s %s betStr: %s, winStr: %s is error", type, lotterMethod, betStr, winStr), e);
        }
        return false;
    }

    public static String getClassName(String type, String pluginType) {
        String className = getRealClassName(type, pluginType, 1);
        return className;
    }


}
