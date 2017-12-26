package com.project.webdriver.service;

import com.project.common.webdriver.WebdriverUtil;
import com.project.webdriver.login.WebLogin;
import com.project.webdriver.model.BiBoHuiMitiBean;
import com.project.webdriver.util.Constant;
import org.apache.commons.collections.map.HashedMap;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by goforit on 2017/9/2.
 */
@Component
@ConfigurationProperties(prefix = "BiBoHuiGrab")
public class BiBoHuiGrab implements Runnable {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private InitConfig initConfig;

    @Autowired
    private BiBoHuiMitiBean biBoHuiMitiBean;

    //货币种类管理器
    private Map<String, String> coinTypeMap = new HashedMap();

    //跳转货币种类的url
    private String coinTypeUrl;

    //货币类型路径
    private String coinTypeXpath;

    //对应的购买url
    private String coinTUXpath;

    //价格对应路径
    private String priceXpath;

    //购买数量路径
    private String buyAccountXpath;

    //购买按钮
    private String buyBtn;

    //错误tips
    private String errorTipsXpath;

    //充值不够
    private String errorTipsValue;

    //支付密码
    private String tradePwdXpath;

    //支付按钮
    private String tradeBtn;

    //交易密码输入框显示
    private String tradePwdWindowXpath;

    //首次运行
    private boolean firstTime;

    //余额路径
    private String cnymoneyXpath;

    //phantomjsPath
    private String phantomjsPath;
    
    @Override
    public void run() {
        execute(biBoHuiMitiBean);
    }


    public void execute(BiBoHuiMitiBean biBoHuiMitiBean) {


        firstTime = true;
        PhantomJSDriver webDriver = null;
        log.info(Constant.INFOStr(getClass().getSimpleName() + " init") + biBoHuiMitiBean.toString());
        try {
            webDriver = WebdriverUtil.getPhantomjsDriver(phantomjsPath);
            WebLogin webLogin = initConfig.getWebLogin(getClass().getSimpleName());
//            webLogin.execute(webDriver,biBoHuiMitiBean.getUserName(),biBoHuiMitiBean.getPassword());
//            log.info(Constant.INFOStr(getClass().getSimpleName() + " login: " + webLogin.isAuthSuccess(webDriver)));
//            //处理货币类型
//            webDriver.navigate().to(coinTypeUrl);
//            List<WebElement> coinTypeElements = webDriver.findElements(By.xpath(coinTypeXpath));
//            List<WebElement> coinTUElements = webDriver.findElements(By.xpath(coinTUXpath));
//            coinTypeElements.forEach((v) -> coinTypeMap.put(v.getText(), coinTUElements.get(coinTypeElements.indexOf(v) * 3).getAttribute("href")));
//
//            coinTypeMap.forEach((k, v) -> log.info(k + "::::" + v));
//
//            //跳转到购买页面
//            webDriver.navigate().to(coinTypeMap.get(biBoHuiMitiBean.getSelectedCoinType()));
//
//            do {
//
//                //判断余额是否充足
//                WebdriverUtil.waitForCondition(webDriver, cnymoneyXpath);
////
////                String yuePrizeStr = webDriver.findElement(By.xpath(cnymoneyXpath)).getText();
////                while (StringUtils.isEmpty(yuePrizeStr)) {
////                    Thread.sleep(100);
////                    yuePrizeStr = webDriver.findElement(By.xpath(cnymoneyXpath)).getText();
////                }
////                Double yue = Double.valueOf(yuePrizeStr);
////                if (yue < biBoHuiMitiBean.getBid()) {
////                    log.info("余额不足 : " + yue);
////                    break;
////                }
//                double givenPize = 0d;
//                int refreshTime = 0;
//                do {
//                    if(biBoHuiMitiBean.isShutdown()){
//                        log.info("shutting down");
//                        break;
//                    }
//                    webDriver.navigate().refresh();
//                    WebElement webElement = WebdriverUtil.waitForCondition(webDriver, priceXpath);
//                    String prizeStr = webElement.getText();
//                    while (StringUtils.isEmpty(prizeStr)) {
//                        Thread.sleep(100);
//                        prizeStr = webElement.getText();
//                    }
//                    givenPize = Double.valueOf(prizeStr);
//                    log.info("the refresh times:" + (refreshTime++) + " prize compaire :" + givenPize + "<>" + biBoHuiMitiBean.getBid());
//                } while (givenPize > biBoHuiMitiBean.getBid());
//                if(biBoHuiMitiBean.isShutdown()){
//                    log.info("shutting down");
//                    break;
//                }
//                //输入购买价格
//                WebdriverUtil.jsSendKey(webDriver, buyAccountXpath, biBoHuiMitiBean.getBuyAccount());
//                webDriver.findElement(By.xpath(buyBtn)).click();
//
//                log.info("errorTipsValue:" + webDriver.findElement(By.xpath(errorTipsXpath)).getText());
//
//                if (firstTime) {
//                    //输入交易密码
//                    WebdriverUtil.waitForShow(webDriver, tradePwdWindowXpath);
//                    log.info("class value:" + webDriver.findElement(By.xpath(tradePwdWindowXpath)).getAttribute("class"));
//                    WebdriverUtil.jsSendKey(webDriver, tradePwdXpath, biBoHuiMitiBean.getTradPwd());
//                    webDriver.findElement(By.xpath(tradeBtn)).click();
//                    WebdriverUtil.waitForFade(webDriver, tradePwdWindowXpath);
//                    log.info("errorTipsValue:" + webDriver.findElement(By.xpath(errorTipsXpath)).getText());
//                    firstTime = false;
//                }
//                biBoHuiMitiBean.decreaseTotalAccount();
//            } while (biBoHuiMitiBean.getTotalAccount() > 0);
        } catch (Throwable e) {
            log.error(Constant.ERRORStr(getClass().getSimpleName() + " Error"), e);
        } finally {
            if (webDriver != null) {
                log.info("close");
                webDriver.quit();
            }
        }
    }

    public String getCoinTypeUrl() {
        return coinTypeUrl;
    }

    public void setCoinTypeUrl(String coinTypeUrl) {
        this.coinTypeUrl = coinTypeUrl;
    }

    public String getCoinTypeXpath() {
        return coinTypeXpath;
    }

    public void setCoinTypeXpath(String coinTypeXpath) {
        this.coinTypeXpath = coinTypeXpath;
    }

    public String getCoinTUXpath() {
        return coinTUXpath;
    }

    public void setCoinTUXpath(String coinTUXpath) {
        this.coinTUXpath = coinTUXpath;
    }

    public String getPriceXpath() {
        return priceXpath;
    }

    public void setPriceXpath(String priceXpath) {
        this.priceXpath = priceXpath;
    }

    public String getBuyAccountXpath() {
        return buyAccountXpath;
    }

    public void setBuyAccountXpath(String buyAccountXpath) {
        this.buyAccountXpath = buyAccountXpath;
    }

    public String getBuyBtn() {
        return buyBtn;
    }

    public void setBuyBtn(String buyBtn) {
        this.buyBtn = buyBtn;
    }

    public String getErrorTipsXpath() {
        return errorTipsXpath;
    }

    public void setErrorTipsXpath(String errorTipsXpath) {
        this.errorTipsXpath = errorTipsXpath;
    }

    public String getErrorTipsValue() {
        return errorTipsValue;
    }

    public void setErrorTipsValue(String errorTipsValue) {
        this.errorTipsValue = errorTipsValue;
    }

    public String getTradePwdXpath() {
        return tradePwdXpath;
    }

    public void setTradePwdXpath(String tradePwdXpath) {
        this.tradePwdXpath = tradePwdXpath;
    }

    public String getTradeBtn() {
        return tradeBtn;
    }

    public void setTradeBtn(String tradeBtn) {
        this.tradeBtn = tradeBtn;
    }

    public String getTradePwdWindowXpath() {
        return tradePwdWindowXpath;
    }

    public void setTradePwdWindowXpath(String tradePwdWindowXpath) {
        this.tradePwdWindowXpath = tradePwdWindowXpath;
    }

    public String getCnymoneyXpath() {
        return cnymoneyXpath;
    }

    public void setCnymoneyXpath(String cnymoneyXpath) {
        this.cnymoneyXpath = cnymoneyXpath;
    }

    public String getPhantomjsPath() {
        return phantomjsPath;
    }

    public void setPhantomjsPath(String phantomjsPath) {
        this.phantomjsPath = phantomjsPath;
    }

    @Override
    public String toString() {
        return "BiBoHuiGrab{" +
                ", coinTypeUrl='" + coinTypeUrl + "\'\n" +
                ", coinTypeXpath='" + coinTypeXpath + "\'\n" +
                ", coinTUXpath='" + coinTUXpath + "\'\n" +
                ", priceXpath='" + priceXpath + "\'\n" +
                ", buyAccountXpath='" + buyAccountXpath + "\'\n" +
                ", buyBtn='" + buyBtn + "\'\n" +
                ", errorTipsXpath='" + errorTipsXpath + "\'\n" +
                ", errorTipsValue='" + errorTipsValue + "\'\n" +
                ", tradePwdXpath='" + tradePwdXpath + "\'\n" +
                ", tradeBtn='" + tradeBtn + "\'\n" +
                ", tradePwdWindowXpath='" + tradePwdWindowXpath + "\'\n" +
                ", firstTime=" + firstTime +
                ", cnymoneyXpath='" + cnymoneyXpath + "\'\n" +
                ", phantomjsPath='" + phantomjsPath + "\'\n" +
                '}';
    }
}
