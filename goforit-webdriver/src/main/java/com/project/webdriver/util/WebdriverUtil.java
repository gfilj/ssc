package com.project.webdriver.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by goforit on 2017/8/25.
 */
public class WebdriverUtil {

    private static Logger log = Logger.getLogger("WebdriverUtil");

    /**
     * 获取文本内容
     *
     * @param webDriver
     * @param xpath
     */
    public static String getValue(WebDriver webDriver, String xpath) {
        return webDriver.findElement(By.xpath(xpath)).getText();
    }

    /**
     * 网络请求监控
     */
    public static void networkRequestMoniter(PhantomJSDriver webDriver) {
        webDriver.executePhantomJS("var page = this;" +
                "page.onResourceRequested = function(request) {\n" +
                "  console.log('Request ' + JSON.stringify(request, undefined, 4));\n" +
                "};\n");
    }

    /**
     * 网络响应监控
     * @param webDriver
     */
    public static void networkResponseMoniter(PhantomJSDriver webDriver) {
        webDriver.executePhantomJS("var page = this;" +
                "page.onResourceReceived = function(response) {\n" +
                "  console.log('Receive ' + JSON.stringify(response, undefined, 4));\n" +
                "};");
    }

    /**
     * 执行Js代码
     */

    public static void jsSendKey(PhantomJSDriver webDriver, String elementXpath, Object content) {
        if(content instanceof String){
            content = "\""+String.valueOf(content)+"\"";
        }
        webDriver.executeScript("arguments[0].value=" + String.valueOf(content), webDriver.findElement(By.xpath(elementXpath)));
    }

    /**
     * 等待页面加载
     *
     * @param webDriver
     * @param xpath
     */
    public static WebElement waitForCondition(WebDriver webDriver, String xpath) {
        WebElement waitForElement = webDriver.findElement(By.xpath(xpath));
        new WebDriverWait(webDriver, 10).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return waitForElement;
            }
        });
        return waitForElement;
    }

    /**
     * 等待页面元素是否显示
     */
    public static void waitForShow(WebDriver webDriver,String xpath){
        WebElement waitForElement = webDriver.findElement(By.xpath(xpath));
        new WebDriverWait(webDriver, 1).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return waitForElement.isDisplayed();
            }
        });
    }
    /**
     * 等待页面元素是否显示
     */
    public static void waitForFade(WebDriver webDriver,String xpath){
        WebElement waitForElement = webDriver.findElement(By.xpath(xpath));
        new WebDriverWait(webDriver, 1).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !waitForElement.isDisplayed();
            }
        });
    }
    /**
     * 等待js加载完成
     *
     * @param webDriver
     * @return
     */
    public static boolean waitForJStoLoad(WebDriver webDriver) {

        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    /**
     * 获得phantomJsDriver
     *
     * @return
     */
    public static PhantomJSDriver getPhantomjsDriver(String path) {

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path);
        PhantomJSDriver phantomJSDriver = new PhantomJSDriver(capabilities);
        //设置大小不然页面元素会找不到
        phantomJSDriver.manage().window().setSize(new Dimension(1280, 1024));
        return phantomJSDriver;
    }


    public static void pageUrlPrint(WebDriver webDriver) {
        log.info(webDriver.getCurrentUrl());
    }

    public static void pageSourcePrint(WebDriver webDriver) {
        log.info(webDriver.getPageSource());
    }


    public static void windowHanderPrint(WebDriver webDriver) {
        String currentWindow = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();
        windowHandles.forEach((v) -> log.info("windows:" + v));
    }

    public static void cookiePrint(WebDriver webDriver) {
        Set<Cookie> cookies = webDriver.manage().getCookies();
        CookieUtil.printCookies(cookies);
    }

}
