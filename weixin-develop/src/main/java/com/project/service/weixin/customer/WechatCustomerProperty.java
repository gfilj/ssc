package com.project.service.weixin.customer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/11/26.
 */
@Component
@ConfigurationProperties(prefix = "WechatCustomerProperty")
public class WechatCustomerProperty {
    private String add;
    private String update;
    private String del;
    private String getkflist;

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }

    public String getGetkflist() {
        return getkflist;
    }

    public void setGetkflist(String getkflist) {
        this.getkflist = getkflist;
    }

    @Override
    public String toString() {
        return "WechatCustomerProperty{" +
                "add='" + add + '\'' +
                ", update='" + update + '\'' +
                ", del='" + del + '\'' +
                ", getkflist='" + getkflist + '\'' +
                '}';
    }
}
