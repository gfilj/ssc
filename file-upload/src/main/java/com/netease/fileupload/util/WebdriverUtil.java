package com.netease.fileupload.util;

import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.util.Set;

/**
 * Created by goforit on 2017/8/25.
 */
public class WebdriverUtil {

    private static Logger log = LogUtil.getLogger(WebdriverUtil.class);

    /**
     * 获取文本内容
     *
     * @param webDriver
     * @param xpath
     */
    public static String getValue(PhantomJSDriver webDriver, String xpath) {
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
     *
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
        if (content instanceof String) {
            content = "\"" + String.valueOf(content) + "\"";
        }
        webDriver.executeScript("arguments[0].value=" + String.valueOf(content), webDriver.findElement(By.xpath(elementXpath)));
    }

    /**
     * 按钮点击
     */
    public static void btnClick(PhantomJSDriver webDriver, String elementXpath) {
        webDriver.findElement(By.xpath(elementXpath)).click();
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

    public static void open(PhantomJSDriver webDriver, String url) {
        webDriver.navigate().to(url);
    }

    /**
     * 等待页面元素是否显示
     */
    public static void waitForShow(WebDriver webDriver, String xpath) {
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
    public static void waitForFade(WebDriver webDriver, String xpath) {
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
    public static boolean waitForJStoLoad(PhantomJSDriver webDriver) {

        WebDriverWait wait = new WebDriverWait(webDriver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) webDriver.executeScript("return jQuery.active") == 0);
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
                return webDriver.executeScript("return document.readyState")
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
        //修改默认的useragent
        String userAgent = "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.41 Safari/535.1";
        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path);
        capabilities.setCapability("phantomjs.page.settings.userAgent", userAgent);
        //修复http错误
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {"--web-security=no", "--ignore-ssl-errors=yes","--load-images=no"});
        PhantomJSDriver phantomJSDriver = new PhantomJSDriver(capabilities);
        //设置窗口大小
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

    public static Set<Cookie> cookiePrint(PhantomJSDriver webDriver) {
        Set<Cookie> cookies = webDriver.manage().getCookies();
        CookieUtil.printCookies(cookies);
        return cookies;
    }


}
