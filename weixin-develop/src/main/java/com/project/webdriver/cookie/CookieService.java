package com.project.webdriver.cookie;

import org.openqa.selenium.Cookie;

import java.util.Set;

/**
 * 实例化cookie
 * Created by goforit on 2017/12/23.
 */
public interface CookieService {

    /**
     * 永久化存储cookie
     * @param cookies
     */
    void save(Set<Cookie> cookies);

    /**
     * 返回Cookie
     * @return
     */
    Set<Cookie> get();
}
