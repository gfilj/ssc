package com.project.service.order;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by goforit on 2017/12/24.
 */
@Component
@ConfigurationProperties(prefix = "OrderProperty")
public class OrderProperty {

    private String orderList;
    private String totalPage;
    private String limitPage;
    private String detailTel;

    public String getDetailTel() {
        return detailTel;
    }

    public void setDetailTel(String detailTel) {
        this.detailTel = detailTel;
    }

    public String getOrderList() {
        return orderList;
    }

    public void setOrderList(String orderList) {
        this.orderList = orderList;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getLimitPage() {
        return limitPage;
    }

    public void setLimitPage(String limitPage) {
        this.limitPage = limitPage;
    }
}
