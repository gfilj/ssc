package com.project.model.sql;

import com.project.common.util.DateUtils;

import java.util.Date;

/**
 * Created by goforit on 2017/12/2.
 */
public class User {

    private int subscribe;// int(11) DEFAULT NULL COMMENT '注册消息',
    private String openid;// varchar(45) DEFAULT NULL,
    private String nickname;// varchar(45) DEFAULT NULL COMMENT '昵称',
    private int sex;// int(11) DEFAULT NULL COMMENT '性别',
    private String language;// varchar(45) DEFAULT NULL COMMENT '语言',
    private String city;// varchar(45) DEFAULT NULL COMMENT '城市',
    private String province;// varchar(45) DEFAULT NULL COMMENT '省份',
    private String country;// varchar(45) DEFAULT NULL COMMENT '国家',
    private String headimgurl;// varchar(1000) DEFAULT NULL COMMENT '头像',
    private Date subscribe_time;// datetime DEFAULT NULL COMMENT '注册',
    private String remark;// varchar(45) DEFAULT NULL COMMENT '备注',
    private String qrCode;//二维码地址
    private int id;

    @Override
    public String toString() {
        return "User{" +
                "subscribe=" + subscribe +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", language='" + language + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", subscribe_time=" + subscribe_time +
                ", remark='" + remark + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", id=" + id +
                ", sex_str='" + sex_str + '\'' +
                ", subscribe_time_str='" + subscribe_time_str + '\'' +
                '}';
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String sex_str;
    private String subscribe_time_str;//日期格式

    public String getSex_str() {
        sex_str = sex==0?"未知":(sex==1?"男":"女");
        return sex_str;
    }

    public void setSex_str(String sex_str) {
        this.sex_str = sex_str;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public Date getSubscribe_time() {
        return subscribe_time;
    }

    public void setSubscribe_time(Date subscribe_time) {
        this.subscribe_time = subscribe_time;
    }

    public String getSubscribe_time_str() {
        if(subscribe_time!=null){
            subscribe_time_str = DateUtils.formatDateTime(subscribe_time);
        }
        return subscribe_time_str;
    }

    public void setSubscribe_time_str(String subscribe_time_str) {
        this.subscribe_time_str = subscribe_time_str;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

}
