package com.project.model.sql;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by goforit on 2018/5/22.
 */
public class PresentRecord {
    private int id;
    private double money;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date moneyTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    private String operator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getMoneyTime() {
        return moneyTime;
    }

    public void setMoneyTime(Date moneyTime) {
        this.moneyTime = moneyTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
