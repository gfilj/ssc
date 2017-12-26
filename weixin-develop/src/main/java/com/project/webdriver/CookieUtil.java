package com.project.webdriver;

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
        log.info(String
                .format("Domain -> name -> value -> expiry -> path"));
        for (Cookie c : cookies)
            log.info(String.format("%s -> %s -> %s -> %s -> %s",
                    c.getDomain(), c.getName(), c.getValue(), c.getExpiry(), c.getPath()));


    }
}
