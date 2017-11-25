package com.project.service.weixin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/10/23.
 */
@Component
@ConfigurationProperties(prefix = "WechatAccessProperty")
public class WechatAccessProperty {

    private String appid;
    private String secret;
    private String tokenUrl;
    private String qrCodeUrl;
    private String expire_seconds;
    private String action_name;
    private String charset;
    private String grant_type;
    private String ticketUrl;
    private String userInfoUrl;
    private String menuUrl;

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }

    public String getTicketUrl() {
        return ticketUrl;
    }

    public void setTicketUrl(String ticketUrl) {
        this.ticketUrl = ticketUrl;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getExpire_seconds() {
        return expire_seconds;
    }

    public void setExpire_seconds(String expire_seconds) {
        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    @Override
    public String toString() {
        return "WechatAccessProperty{" +
                "appid='" + appid + '\'' +
                ", secret='" + secret + '\'' +
                ", tokenUrl='" + tokenUrl + '\'' +
                ", qrCodeUrl='" + qrCodeUrl + '\'' +
                ", expire_seconds='" + expire_seconds + '\'' +
                ", action_name='" + action_name + '\'' +
                ", charset='" + charset + '\'' +
                ", grant_type='" + grant_type + '\'' +
                ", ticketUrl='" + ticketUrl + '\'' +
                ", userInfoUrl='" + userInfoUrl + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                '}';
    }
}
