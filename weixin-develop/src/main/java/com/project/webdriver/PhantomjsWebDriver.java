package com.project.webdriver;

import com.project.webdriver.cookie.CookieService;
import com.project.webdriver.login.WebdriverProperty;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * Created by goforit on 2017/8/25.
 */
@Component
public class PhantomjsWebDriver implements InitializingBean {

    Logger log = Logger.getLogger(getClass());

    public final String SCRREN_SHOT = "./scrrenshot/";
    private PhantomJSDriver phantomJSDriver;

    @Autowired
    private WebdriverProperty webdriverProperty;

    /**
     * 初始化设置
     */
    public void init(String path) {
        if (phantomJSDriver == null) {
            phantomJSDriver = getPhantomjsDriver(path);
            log.info("phantomjs 初始化成功!");
        }
    }

    /**
     * 销毁操作
     */
    public void destroy() {
        phantomJSDriver.quit();
        phantomJSDriver = null;
        log.info("释放成功");
    }

    /**
     * 按钮点击操作
     */
    public void click(String xpath) {
        phantomJSDriver.findElement(By.xpath(xpath)).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("点击后等待报错！", e);
        }
    }

    /**
     * 请求的时候添加cookie
     *
     * @param cookie
     */
    public void addCookie(Cookie cookie) {
        phantomJSDriver.manage().addCookie(cookie);
    }

    /**
     * 返回当前的所有cookie
     *
     * @return
     */
    public Set<Cookie> getCookies() {
        return get().manage().getCookies();
    }

    /**
     * 向指定元素发送指定信息
     */
    public void sendKeys(String xpath, String message) {
        //先清空，然后在填入
        WebElement element = phantomJSDriver.findElement(By.xpath(xpath));
        element.clear();
        element.sendKeys(message);
    }

    /**
     * 判断页面元素是否存在
     */
    public boolean exist(String xpath) {
        try{
            WebElement element = phantomJSDriver.findElement(By.xpath(xpath));
            element.click();
            return true;
        }catch (Throwable e){
            return false;
        }
    }

    /**
     * refresh
     * 刷新防止js卡住
     */
    public void refresh() {
        phantomJSDriver.navigate().refresh();
    }

    /**
     * 导航访问，可有撤回操作
     *
     * @param path
     */
    public void navigateto(String path) {
        phantomJSDriver.navigate().to(path);
    }

    /**
     * 获取当前操作的driver
     *
     * @return
     */
    public PhantomJSDriver get() {
        return phantomJSDriver;
    }

    /**
     * 截取屏幕
     *
     * @throws IOException
     */
    public void takeScreenshot(String filename) {
        File screenshotAs = phantomJSDriver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotAs, new File(SCRREN_SHOT + filename));
        } catch (IOException e) {
            log.error("存储截图报错：" + filename, e);
        }
    }

    /**
     * 获取文本内容
     *
     * @param xpath
     */
    public String getValue(String xpath) {
        return phantomJSDriver.findElement(By.xpath(xpath)).getText();
    }

    /**
     * 获取属性内容
     *
     * @param xpath
     */
    public String getAttribute(String xpath, String attribute) {
        return phantomJSDriver.findElement(By.xpath(xpath)).getAttribute(attribute);
    }


    /**
     * 网络请求监控
     */
    public void networkRequestMoniter() {
        phantomJSDriver.executePhantomJS("var page = this;" +
                "page.onResourceRequested = function(request) {\n" +
                "  console.log('Request ' + JSON.stringify(request, undefined, 4));\n" +
                "};\n");
    }

    /**
     * 网络响应监控
     */
    public void networkResponseMoniter() {
        phantomJSDriver.executePhantomJS("var page = this;" +
                "page.onResourceReceived = function(response) {\n" +
                "  console.log('Receive ' + JSON.stringify(response, undefined, 4));\n" +
                "};");
    }

    /**
     * 执行Js代码
     */

    public void jsSendKey(String elementXpath, Object content) {
        if (content instanceof String) {
            content = "\"" + String.valueOf(content) + "\"";
        }
        phantomJSDriver.executeScript("arguments[0].value=" + String.valueOf(content), phantomJSDriver.findElement(By.xpath(elementXpath)));
    }

    /**
     * 期待在页面上可以找到此元素
     */
    public WebElement waitForLocation(String xpath) {
        WebDriverWait wait = new WebDriverWait(phantomJSDriver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        return phantomJSDriver.findElement(By.xpath(xpath));
    }

    /**
     * 等待元素是否可以被点击
     *
     * @param xpath
     */
    public WebElement waitForClick(String xpath) {
        WebElement waitForElement = phantomJSDriver.findElement(By.xpath(xpath));
        WebDriverWait wait = new WebDriverWait(phantomJSDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(waitForElement));
        return waitForElement;
    }

    /**
     * 等待页面元素是否显示
     */
    public void waitForShow(String xpath) {
        WebElement waitForElement = phantomJSDriver.findElement(By.xpath(xpath));
        new WebDriverWait(phantomJSDriver, 10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return waitForElement.isDisplayed();
            }
        });
    }

    /**
     * 等待页面元素是否消失
     */
    public void waitForFade(String xpath) {
        WebElement waitForElement = phantomJSDriver.findElement(By.xpath(xpath));
        new WebDriverWait(phantomJSDriver, 10).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !waitForElement.isDisplayed();
            }
        });
    }

    /**
     * 等待js加载完成
     *
     * @return
     */
    public boolean waitForJStoLoad() {

        WebDriverWait wait = new WebDriverWait(phantomJSDriver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) phantomJSDriver).executeScript("return jQuery.active") == 0);
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
                return ((JavascriptExecutor) phantomJSDriver).executeScript("return document.readyState")
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
    public PhantomJSDriver getPhantomjsDriver(String path) {

        DesiredCapabilities capabilities = DesiredCapabilities.phantomjs();
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path);
        PhantomJSDriver phantomJSDriver = new PhantomJSDriver(capabilities);
        //设置大小不然页面元素会找不到
        phantomJSDriver.manage().window().setSize(new Dimension(1920, 1080));
        return phantomJSDriver;
    }

    /**
     * 当前页面打印
     */
    public void pageUrlPrint() {
        log.info(phantomJSDriver.getCurrentUrl());
    }

    /**
     * 当前页面打印
     */
    public void pageSourcePrint() {
        log.info(phantomJSDriver.getPageSource());
    }

    /**
     * 当前页面打印
     */
    public void windowHanderPrint() {
        String currentWindow = phantomJSDriver.getWindowHandle();
        Set<String> windowHandles = phantomJSDriver.getWindowHandles();
        windowHandles.forEach((v) -> log.info("windows:" + v));
    }

    /**
     * 当前cookie信息打印
     */
    public void cookiePrint() {
        Set<Cookie> cookies = phantomJSDriver.manage().getCookies();
        CookieUtil.printCookies(cookies);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        init(webdriverProperty.getPhantomjsPath());
    }
}
