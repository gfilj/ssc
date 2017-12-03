package com.project.model.sql;

import java.util.Date;

/**
 * Created by goforit on 2017/12/3.
 */
public class UserRelation {
    private String introducer;// varchar(45) DEFAULT NULL COMMENT '介绍人',
    private String newmember;// varchar(45) DEFAULT NULL COMMENT '组织者',
    private int releation;// int(11) DEFAULT NULL COMMENT '0未知，1为绑定，2为解绑\n',
    private Date lmodify;// datetime DEFAULT NULL COMMENT '修改时间',
    private String introducername;// varchar(45) DEFAULT NULL COMMENT '介绍人名字',
    private String newmembername;// varchar(45) DEFAULT NULL COMMENT '新粉丝名字',

    private String lomodifyStr;//展示用

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getNewmember() {
        return newmember;
    }

    public void setNewmember(String newmember) {
        this.newmember = newmember;
    }

    public int getReleation() {
        return releation;
    }

    public void setReleation(int releation) {
        this.releation = releation;
    }

    public Date getLmodify() {
        return lmodify;
    }

    public void setLmodify(Date lmodify) {
        this.lmodify = lmodify;
    }

    public String getIntroducername() {
        return introducername;
    }

    public void setIntroducername(String introducername) {
        this.introducername = introducername;
    }

    public String getNewmembername() {
        return newmembername;
    }

    public void setNewmembername(String newmembername) {
        this.newmembername = newmembername;
    }

    public String getLomodifyStr() {
        return lomodifyStr;
    }

    public void setLomodifyStr(String lomodifyStr) {
        this.lomodifyStr = lomodifyStr;
    }

    @Override
    public String toString() {
        return "UserRelation{" +
                "introducer='" + introducer + '\'' +
                ", newmember='" + newmember + '\'' +
                ", releation=" + releation +
                ", lmodify=" + lmodify +
                ", introducername='" + introducername + '\'' +
                ", newmembername='" + newmembername + '\'' +
                ", lomodifyStr='" + lomodifyStr + '\'' +
                '}';
    }
}
