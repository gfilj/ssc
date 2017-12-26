package com.project.webdriver.cookie.impl;

import com.project.common.storage.LocalStorage;
import com.project.common.util.LogUtil;
import com.project.webdriver.CookieUtil;
import com.project.webdriver.cookie.CookieService;
import org.openqa.selenium.Cookie;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by goforit on 2017/12/23.
 */
@Service
public class CookieServiceImpl extends LocalStorage<Set<Cookie>> implements CookieService,InitializingBean {

    private Logger logger = LogUtil.getLogger(getClass());

    private Set<Cookie> cookies;

    private static final String cookieStoragePath = "./cookie/shop.jd.com";
    @Override
    public void save(Set<Cookie> cookies) {
        this.cookies = cookies;
        write(cookies);
    }

    @Override
    public Set<Cookie> get() {
        if(cookies == null){
            cookies = read();
        }
        return cookies;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setPath(cookieStoragePath);
    }
}
