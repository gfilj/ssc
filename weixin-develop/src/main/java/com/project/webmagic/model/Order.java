package com.project.webmagic.model;

import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

/**
 * Created by goforit on 2017/12/23.
 */
@TargetUrl("https://shop.jd.com/index.action")
@ExtractBy(value = "/html/body/div[2]/div/div[3]/div[1]/div/div/div[2]/table[2]/tbody",multi = true)
public class Order {
    @ExtractBy("//a[@class=suctext]/text()")
    private String no;//订单编号
    @ExtractBy("//a[@clstag=pageclick|keycount|order_shop_201508177|7]/text()")
    private String goods;//货物明细
    @ExtractBy("//a[@class=suctext]/@href")
    private String detailLink;//详情链接，
    private String payRealName;
    private String tel;


    private static final String head="https:";

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }


    public String getPayRealName() {
        return payRealName;
    }

    public void setPayRealName(String payRealName) {
        this.payRealName = payRealName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getDetailLink() {
        return head + detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }


    @Override
    public String toString() {
        return "Order{" +
                "no='" + no + '\'' +
                ", goods='" + goods + '\'' +
                ", detailLink='" + getDetailLink() + '\'' +
                ", payRealName='" + payRealName + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
