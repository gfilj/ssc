package com.project.webdriver.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/12/17.
 */
@Component
@ConfigurationProperties(prefix = "WebdriverProperty")
public class WebdriverProperty {
    private String phantomjsPath;//pathtonjs路径
    private String startUrl;//网站的开始地址
    private String qrCodeXpath;//二维码元素路径
    private String authXpath;//出现用户名和密码的元素路径
    private String iframeSrc;//登录框iframe地址
    private String userName;//用户名
    private String password;//密码
    private String userXpath;//用户名元素路径
    private String passXpath;//密码元素路径
    private String loginXpath;//登录按钮路径
    private String smsVerification; //短信验证路径
    private String authSuccessXpath;//认证成功路径
    private String successUrl;//登录成功的路径
    private String verificationImg;//验证码图片
    private String verificationCode;//验证码输入框

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationImg() {
        return verificationImg;
    }

    public void setVerificationImg(String verificationImg) {
        this.verificationImg = verificationImg;
    }

    public String getSmsVerification() {
        return smsVerification;
    }

    public void setSmsVerification(String smsVerification) {
        this.smsVerification = smsVerification;
    }

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
    }

    public String getAuthXpath() {
        return authXpath;
    }

    public void setAuthXpath(String authXpath) {
        this.authXpath = authXpath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserXpath() {
        return userXpath;
    }

    public void setUserXpath(String userXpath) {
        this.userXpath = userXpath;
    }

    public String getPassXpath() {
        return passXpath;
    }

    public void setPassXpath(String passXpath) {
        this.passXpath = passXpath;
    }

    public String getLoginXpath() {
        return loginXpath;
    }

    public void setLoginXpath(String loginXpath) {
        this.loginXpath = loginXpath;
    }

    public String getAuthSuccessXpath() {
        return authSuccessXpath;
    }

    public void setAuthSuccessXpath(String authSuccessXpath) {
        this.authSuccessXpath = authSuccessXpath;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getPhantomjsPath() {
        return phantomjsPath;
    }

    public void setPhantomjsPath(String phantomjsPath) {
        this.phantomjsPath = phantomjsPath;
    }

    public String getIframeSrc() {
        return iframeSrc;
    }

    public void setIframeSrc(String iframeSrc) {
        this.iframeSrc = iframeSrc;
    }

    public String getQrCodeXpath() {
        return qrCodeXpath;
    }

    public void setQrCodeXpath(String qrCodeXpath) {
        this.qrCodeXpath = qrCodeXpath;
    }

    @Override
    public String toString() {
        return "WebdriverProperty{" +
                "phantomjsPath='" + phantomjsPath + '\'' +
                ", startUrl='" + startUrl + '\'' +
                ", qrCodeXpath='" + qrCodeXpath + '\'' +
                ", authXpath='" + authXpath + '\'' +
                ", iframeSrc='" + iframeSrc + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userXpath='" + userXpath + '\'' +
                ", passXpath='" + passXpath + '\'' +
                ", loginXpath='" + loginXpath + '\'' +
                ", smsVerification='" + smsVerification + '\'' +
                ", authSuccessXpath='" + authSuccessXpath + '\'' +
                ", successUrl='" + successUrl + '\'' +
                ", verificationImg='" + verificationImg + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }


}
