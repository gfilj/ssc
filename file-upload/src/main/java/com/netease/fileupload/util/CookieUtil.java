package com.netease.fileupload.util;

import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by goforit on 2017/8/25.
 */
public class CookieUtil {

    private static Logger log = LoggerFactory.getLogger(CookieUtil.class);

    public static void printCookies(Set<Cookie> cookies) {
        String str = "Domain -> name -> value -> expiry -> path\n";
        for (Cookie c : cookies)
            str +=(String.format("%s -> %s -> %s -> %s -> %s\n",
                    c.getDomain(), c.getName(), c.getValue(), c.getExpiry(), c.getPath()));
        log.info(Constant.INFOStr("cookies") + str);

    }
}
