package com.project.webdriver.login;

import com.project.webdriver.util.Constant;
import com.project.webdriver.util.WebdriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by goforit on 2017/8/27.
 */
public class WebLogin {
    private Logger log = LoggerFactory.getLogger(getClass());
    private String startUrl;
    private String authXpath;
    private String userName;
    private String password;
    private String userXpath;
    private String passXpath;
    private String loginXpath;
    private String authSuccessXpath;
    private String successUrl;

    public WebLogin(String startUrl, String authXpath, String userName, String password, String userXpath, String passXpath, String loginXpath, String authSuccessXpath, String successUrl) {
        this.startUrl = startUrl;
        this.authXpath = authXpath;
        this.userName = userName;
        this.password = password;
        this.userXpath = userXpath;
        this.passXpath = passXpath;
        this.loginXpath = loginXpath;
        this.authSuccessXpath = authSuccessXpath;
        this.successUrl = successUrl;
    }

    /**

     */
    public void execute(PhantomJSDriver webDriver) throws Exception {
        execute(webDriver, this.userName, this.password);
    }

    public void execute(PhantomJSDriver webDriver, String userName, String password) throws Exception {
        try {
//
            webDriver.get(startUrl);
            if(isAuthSuccess(webDriver)){
                return;
            }
            //判断页面是否加载出
            WebElement authElement = WebdriverUtil.waitForCondition(webDriver, authXpath);
            //点击切换登录方式
            authElement.click();
            //等待登录按钮出现
            WebElement loginElement = WebdriverUtil.waitForCondition(webDriver, loginXpath);
            //输入用户名和密码

            webDriver.findElement(By.xpath(userXpath)).sendKeys(userName);
            webDriver.findElement(By.xpath(passXpath)).sendKeys(password);
            loginElement.click();

            //刷新当前页（防止js卡住页面）
            webDriver.navigate().refresh();

        } catch (Throwable e) {
            log.error(Constant.ERRORStr(getClass().getSimpleName() + " Error"), e);
            throw e;
        }
    }

    public boolean isAuthSuccess(WebDriver webDriver) {
        try {
            WebElement element = webDriver.findElement(By.xpath(authSuccessXpath));
            if (element == null) {
                log.info("AuthSuccess is not found!");
            }
            return element != null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "WebLogin{" +
                "\nstartUrl='" + startUrl + "\'\n" +
                ", authXpath='" + authXpath + "\'\n" +
                ", userName='" + userName + "\'\n" +
                ", password='" + password + "\'\n" +
                ", userXpath='" + userXpath + "\'\n" +
                ", passXpath='" + passXpath + "\'\n" +
                ", loginXpath='" + loginXpath + "\'\n" +
                ", authSuccessXpath='" + authSuccessXpath + "\'\n" +
                ", successUrl='" + successUrl + "\'\n" +
                '}';
    }
}
