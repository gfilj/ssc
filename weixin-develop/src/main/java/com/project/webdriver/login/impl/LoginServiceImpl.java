package com.project.webdriver.login.impl;

import com.project.common.exception.BusinessException;
import com.project.common.util.LogUtil;
import com.project.webdriver.PhantomjsWebDriver;
import com.project.webdriver.cookie.CookieService;
import com.project.webdriver.login.LoginService;
import com.project.webdriver.login.WebdriverProperty;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

/**
 * Created by goforit on 2017/12/17.
 */
@Service
public class LoginServiceImpl implements LoginService {


    private Log logger = LogUtil.getLogger(getClass());

    @Autowired
    private WebdriverProperty webdriverProperty;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private PhantomjsWebDriver phantomjsWebDriver;


    @Override
    public void login() throws BusinessException {
        try {
            phantomjsWebDriver.networkRequestMoniter();
            //登录
            phantomjsWebDriver.get().get(webdriverProperty.getStartUrl());
            logger.info("进入页面");
            if (isSuccess()) {
                return;
            }
            //判断页面是否加载出
            WebElement authElement = phantomjsWebDriver.waitForLocation(webdriverProperty.getAuthXpath());
            logger.info("用户名密码按钮已经加载出");
            //点击切换登录方式
            authElement.click();

            String loginUrl = phantomjsWebDriver.getAttribute(webdriverProperty.getIframeSrc(), "src");
            logger.info("登录iframe的src属性：" + loginUrl);

            phantomjsWebDriver.takeScreenshot("beforeLogin.png");
            phantomjsWebDriver.navigateto(loginUrl);

            phantomjsWebDriver.waitForLocation(webdriverProperty.getLoginXpath());

            sendUP();
            if (phantomjsWebDriver.exist(webdriverProperty.getVerificationCode())) {
                logger.info("进入输入验证码逻辑！");
                phantomjsWebDriver.takeScreenshot("verificationImg.png");
                //等待输入验证码
                return;
            } else {
                phantomjsWebDriver.click(webdriverProperty.getLoginXpath());
                if (phantomjsWebDriver.exist(webdriverProperty.getVerificationCode())) {
                    logger.info("进入输入验证码逻辑！");
                    phantomjsWebDriver.takeScreenshot("verificationImg.png");
                    //等待输入验证码
                    return;
                }
            }
            logger.info("进入登录成功逻辑！");
            waitForSuccess();
            saveCookie();
            logger.info("登录成功！");


            //截取屏幕
        } catch (Throwable e) {
            try {
                throw e;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }


    @Override
    public boolean isSuccess() {
        try {
            addCookie();
            phantomjsWebDriver.navigateto(webdriverProperty.getStartUrl());
            phantomjsWebDriver.refresh();
            WebElement element = phantomjsWebDriver.get().findElement(By.xpath(webdriverProperty.getSuccessUrl()));
            if (element == null) {
                logger.info("未登录!");
                return false;
            }
            logger.info("登录成功!");
            return true;
        } catch (Exception e) {
            logger.info("未登录!");
            return false;
        }
    }


    @Override
    public void enterCode(String code) throws BusinessException {
        phantomjsWebDriver.sendKeys(webdriverProperty.getVerificationCode(), code);
        try {
            phantomjsWebDriver.click(webdriverProperty.getLoginXpath());
            waitForSuccess();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String currentSource() {
        return phantomjsWebDriver.get().getPageSource();
    }

    /**
     * 等待登录成功
     *
     * @throws IOException
     */
    public void waitForSuccess() throws IOException {
        phantomjsWebDriver.takeScreenshot("beforeSuccess.png");
        phantomjsWebDriver.waitForLocation(webdriverProperty.getSuccessUrl());
        phantomjsWebDriver.takeScreenshot("success.png");
    }

    /**
     * 添加cookie
     */
    public void addCookie() {
        Set<Cookie> cookies = cookieService.get();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                phantomjsWebDriver.addCookie(cookie);
            }
        }
    }

    /**
     * 存储cookie
     */
    public void saveCookie() {
        cookieService.save(phantomjsWebDriver.getCookies());
    }

    /**
     * 输入用户名和密码
     *
     * @throws IOException
     */
    public void sendUP() throws IOException {
        //输入用户名和密码
        phantomjsWebDriver.sendKeys(webdriverProperty.getUserXpath(), webdriverProperty.getUserName());
        phantomjsWebDriver.sendKeys(webdriverProperty.getPassXpath(), webdriverProperty.getPassword());
        phantomjsWebDriver.takeScreenshot("login.png");
    }

}
