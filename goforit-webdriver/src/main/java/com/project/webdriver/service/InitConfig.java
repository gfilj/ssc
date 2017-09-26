package com.project.webdriver.service;

import com.project.webdriver.login.WebLogin;
import com.project.webdriver.util.Constant;
import com.project.webdriver.util.Row;
import com.project.webdriver.util.WebdriverUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by goforit on 2017/8/27.
 */
@Component
@ConfigurationProperties(prefix = "webLogin")
public class InitConfig implements InitializingBean {

    private Logger log = Logger.getLogger(getClass().getSimpleName());


    private List<Map<String, String>> originalList = new LinkedList();
    private Map<String, WebLogin> webLoginManager = new HashedMap();

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(Constant.INFOStr("InitConfig"));
        originalList.forEach((v) -> {
            Row row = Row.getInstance(v);
            String k = row.getString("name");
            webLoginManager.put(k, new WebLogin(
                    row.getString("startUrl"),
                    row.getString("authXpath"),
                    row.getString("userName"),
                    row.getString("password"),
                    row.getString("userXpath"),
                    row.getString("passXpath"),
                    row.getString("loginXpath"),
                    row.getString("authSuccessXpath"),
                    row.getString("successUrl")
                    )
            );
            log.info(String.format("k:%s,v:%s",k,webLoginManager.get(k)));
        });
    }

    public List<Map<String, String>> getOriginalList() {
        return originalList;
    }

    public WebLogin getWebLogin(String name){
        return webLoginManager.get(name);
    }
}
