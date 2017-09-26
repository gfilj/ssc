package com.project.webdriver.model;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by goforit on 2017/9/5.
 */
public class User implements Serializable {

    private static final long serialVersionUID = 8383901821872620925L;

    private String username;
    private int days;
    private Date valideDate;
    private String dateStr;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
        setValideDate(DateTime.now().plusDays(days).toDate());
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setDateStr(time.format(getValideDate()));
    }

    public Date getValideDate() {
        return valideDate;
    }

    public void setValideDate(Date valideDate) {
        this.valideDate = valideDate;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public User plusDay(int days){
        DateTime dateTime = new DateTime(valideDate);
        dateTime.plusDays(days);
        setValideDate(dateTime.toDate());
        SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        setDateStr(time.format(getValideDate()));
        this.days += days;
        return this;
    }

}
