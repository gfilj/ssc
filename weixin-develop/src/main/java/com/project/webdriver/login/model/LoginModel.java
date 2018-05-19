package com.project.webdriver.login.model;

/**
 * 登录model
 * Created by goforit on 2018/5/7.
 */
public class LoginModel {

    private boolean result;
    private String qrCodeUrl;

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "result='" + result + '\'' +
                ", qrCodeUrl='" + qrCodeUrl + '\'' +
                '}';
    }
}
