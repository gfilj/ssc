package com.project.webmagic.model;

import com.alibaba.fastjson.annotation.JSONField;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.Formatter;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.Date;

/**
 * Created by goforit on 2017/12/24.
 * 电商、电商手机号、电商昵称、姓名、送货地址、品名、规格描述、数量、单位、记录日期、电商订单号、备注
 */
public class OrderDetailDB {
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date submitOrderTime;//下单时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date paySuccessTime;//付款时间
    private String no;//订单号
    private String realname;//姓名
    private String tel;//电话手机号
    private String mark;//备注
    private String address;//送货地址
    private String productnumber;//商品编号
    private String productname;//品名
    private Integer quantity;//数量
    private String jingdname;//电商
    //页面序号
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getJingdname() {
        return jingdname;
    }

    public void setJingdname(String jingdname) {
        this.jingdname = jingdname;
    }

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getSubmitOrderTime() {
        return submitOrderTime;
    }

    public void setSubmitOrderTime(Date submitOrderTime) {
        this.submitOrderTime = submitOrderTime;
    }

    public Date getPaySuccessTime() {
        return paySuccessTime;
    }

    public void setPaySuccessTime(Date paySuccessTime) {
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
                ", mark='" + mark + '\'' +
                ", address='" + address + '\'' +
                ", productnumber='" + productnumber + '\'' +
                ", productname='" + productname + '\'' +
                ", quantity=" + quantity +
                ", jingdname='" + jingdname + '\'' +
                '}';
    }
}
