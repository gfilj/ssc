package com.project.webdriver.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/9/4.
 */
@Component
@ConfigurationProperties(prefix = "BiBoHuiMitiBean")
public class BiBoHuiMitiBean {
    private String userName;
    private String password;
    private String selectedCoinType;
    private Double bid;
    private String tradPwd;
    private int buyAccount;
    private int totalAccount;
    private boolean shutdown;

    public BiBoHuiMitiBean(String userName, String password, String selectedCoinType, Double bid, String tradPwd, int buyAccount, int totalAccount, boolean shutdown) {
        this.userName = userName;
        this.password = password;
        this.selectedCoinType = selectedCoinType;
        this.bid = bid;
        this.tradPwd = tradPwd;
        this.buyAccount = buyAccount;
        this.totalAccount = totalAccount;
        this.shutdown = shutdown;
    }

    public BiBoHuiMitiBean() {
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

    public String getSelectedCoinType() {
        return selectedCoinType;
    }

    public void setSelectedCoinType(String selectedCoinType) {
        this.selectedCoinType = selectedCoinType;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public String getTradPwd() {
        return tradPwd;
    }

    public void setTradPwd(String tradPwd) {
        this.tradPwd = tradPwd;
    }

    public int getBuyAccount() {
        return buyAccount;
    }

    public void setBuyAccount(int buyAccount) {
        this.buyAccount = buyAccount;
    }

    public int getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount(int totalAccount) {
        this.totalAccount = totalAccount;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public void decreaseTotalAccount(){
        totalAccount-=buyAccount;
    }

    @Override
    public String toString() {
        return "BiBoHuiMitiBean{" +
                "userName='" + userName + "\'\n" +
                ", password='" + password + "\'\n" +
                ", selectedCoinType='" + selectedCoinType + "\'\n" +
                ", bid=" + bid + "\n" +
                ", tradPwd='" + tradPwd + "\'\n" +
                ", buyAccount=" + buyAccount + "\n" +
                ", totalAccount=" + totalAccount + "\n" +
                ", shutdown=" + shutdown +
                '}';
    }
}
