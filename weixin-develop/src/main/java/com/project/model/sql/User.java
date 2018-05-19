package com.project.model.sql;

import com.alibaba.fastjson.annotation.JSONField;
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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date subscribe_time;// datetime DEFAULT NULL COMMENT '注册',
    private String remark;// varchar(45) DEFAULT NULL COMMENT '备注',
    private String qrCode;//二维码地址
    private String qrCode1;//二维码地址1
    private String tel;//电话号码
    private String name;//姓名
    private String identity;//身份
    private String identitytime;//身份时间
    private String highcode;//最高分销代码
    private String supercode;//上级分销
    private String firstbuycode;//首购优惠码
    private String firstbuycodeuse;//首购优惠码使用
    private String wechat;//官方微信

    private int id;

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getQrCode1() {
        return qrCode1;
    }

    public void setQrCode1(String qrCode1) {
        this.qrCode1 = qrCode1;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getIdentitytime() {
        return identitytime;
    }

    public void setIdentitytime(String identitytime) {
        this.identitytime = identitytime;
    }

    public String getHighcode() {
        return highcode;
    }

    public void setHighcode(String highcode) {
        this.highcode = highcode;
    }

    public String getSupercode() {
        return supercode;
    }

    public void setSupercode(String supercode) {
        this.supercode = supercode;
    }

    public String getFirstbuycode() {
        return firstbuycode;
    }

    public void setFirstbuycode(String firstbuycode) {
        this.firstbuycode = firstbuycode;
    }

    public String getFirstbuycodeuse() {
        return firstbuycodeuse;
    }

    public void setFirstbuycodeuse(String firstbuycodeuse) {
        this.firstbuycodeuse = firstbuycodeuse;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
