package com.project.webdriver.service;

import com.project.common.webdriver.WebdriverUtil;
import com.project.webdriver.util.Constant;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.phantomjs.PhantomJSDriver;


/**
 * Created by goforit on 2017/8/25.
 */
public class QQGrab implements Runnable{

    private static Logger log = Logger.getLogger("QQGrab");

    @Override
    public void run() {
        PhantomJSDriver webDriver = null;
        log.info("init");

        try {
            webDriver = WebdriverUtil.getPhantomjsDriver("");
            webDriver.get("http://w.qq.com/");
            //判断页面是否加载出
            WebdriverUtil.waitForCondition(webDriver, "//*[@id=\"switcher_plogin\"]");
            //点击切换登录方式
            webDriver.findElement(By.xpath("//*[@id=\"switcher_plogin\"]")).click();
            //等待登录按钮出现
            WebdriverUtil.waitForCondition(webDriver, "//*[@id=\"login_button\"]");
            //输入用户名和密码
            webDriver.findElement(By.xpath("//*[@id=\"u\"]")).sendKeys("13718189105");
            webDriver.findElement(By.xpath("")).sendKeys("Lcq195302*");

        } catch (Throwable e) {
            log.error(Constant.ERRORStr("QQGrab Error"), e);
        } finally {
            if (webDriver != null) {
                webDriver.close();
            }
        }
    }
}
