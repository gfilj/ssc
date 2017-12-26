package com.project.webdriver.service;

import com.project.common.webdriver.WebdriverUtil;
import com.project.webdriver.login.WebLogin;
import com.project.webdriver.util.Constant;
import org.apache.log4j.Logger;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/8/26.
 */
@Component
public class IcoInfoGrab implements Runnable{

    private Logger log = Logger.getLogger(getClass().getSimpleName());
    @Autowired
    private InitConfig initConfig;
    @Override
    public void run() {
        PhantomJSDriver webDriver = null;
        log.info("init");

        try {
            webDriver = WebdriverUtil.getPhantomjsDriver("");
            WebLogin webLogin = initConfig.getWebLogin(getClass().getSimpleName());
            webLogin.execute(webDriver);
//            WebdriverUtil.pageSourcePrint(webDriver);

            log.info(Constant.INFOStr(getClass().getSimpleName() + " login: " + webLogin.isAuthSuccess(webDriver)));
        } catch (Throwable e) {
            log.error(Constant.ERRORStr(getClass().getSimpleName() + " Error"), e);
        } finally {
            if (webDriver != null) {
                log.info("close");
                webDriver.executePhantomJS("phantom.exit()");
                webDriver.close();
            }
        }
    }
}
