package com.project.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.project.model.sql.User;

import java.util.Date;
import java.util.List;

/**
 * Created by goforit on 2018/6/3.
 */
public class UserSearchVO {
    private String nickname;
    private String openid;
    private String sex;
    private String language;
    private String city;
    private String province;// varchar(45) DEFAULT NULL COMMENT '省份',
    private String country;// varchar(45) DEFAULT NULL COMMENT '国家',
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


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "SearchVO{" +
                "nickname='" + nickname + '\'' +
                ", userList=" + userList +
                '}';
    }
}
