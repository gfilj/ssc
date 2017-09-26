package com.project.property;

/**
 * Created by goforit on 2017/8/18.
 */
public enum LotteryType {

    SSC("ssc");


    private String value;

    LotteryType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
