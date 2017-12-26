package com.project.webmagic.model;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by goforit on 2017/12/24.
 */
public class OrderDetail {
    @ExtractBy("/html/body/div[1]/div[1]/ul/li[1]/p[2]/text()")
    private String submitOrderTime;
    @ExtractBy("/html/body/div[1]/div[1]/ul/li[2]/p[2]/text()")
    private String paySuccessTime;
    @ExtractBy("/html/body/div[1]/div[2]/p[1]/span[2]/text()")
    private String no;
    @ExtractBy("//*[@id=\"receiveData\"]/tbody/tr[2]/td[2]/text()")
    private String realname;
    @ExtractBy("//*[@id=\"mobile\"]/text()")
    private String tel;

    public String getSubmitOrderTime() {
        return submitOrderTime;
    }

    public void setSubmitOrderTime(String submitOrderTime) {
        this.submitOrderTime = submitOrderTime;
    }

    public String getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(String paySuccessTime) {
        this.paySuccessTime = paySuccessTime;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "submitOrderTime='" + submitOrderTime + '\'' +
                ", paySuccessTime='" + paySuccessTime + '\'' +
                ", no='" + no + '\'' +
                ", realname='" + realname + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
