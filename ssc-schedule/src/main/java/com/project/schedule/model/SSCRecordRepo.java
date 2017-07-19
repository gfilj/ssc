package com.project.schedule.model;

import java.io.Serializable;

/**
 * Created by goforit on 2017/7/19.
 */
public class SSCRecordRepo implements Serializable {
    private int id; //主键
    private String gamer;//'玩家',
    private String type;//'玩法 按照:显示上的坐标 第一坐标为上面选择项目,第二坐标为下面选择项目,按照自然排序,中间分隔符为-例如(1-1)',
    private String data;//'按照页面的选项英文对应字符串传入'
    private int mutiple;//'倍数',
    private int status;//'0为加入购物篮 1为下注',
    private int rebate;//'返点'
    private int note;//'注数'
    private int reprize;//'返还价钱'
    private int allprize;//'共投注价钱'
    private boolean settlement;//'是否结算'

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGamer() {
        return gamer;
    }

    public void setGamer(String gamer) {
        this.gamer = gamer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getMutiple() {
        return mutiple;
    }

    public void setMutiple(int mutiple) {
        this.mutiple = mutiple;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRebate() {
        return rebate;
    }

    public void setRebate(int rebate) {
        this.rebate = rebate;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getReprize() {
        return reprize;
    }

    public void setReprize(int reprize) {
        this.reprize = reprize;
    }

    public int getAllprize() {
        return allprize;
    }

    public void setAllprize(int allprize) {
        this.allprize = allprize;
    }

    public boolean isSettlement() {
        return settlement;
    }

    public void setSettlement(boolean settlement) {
        this.settlement = settlement;
    }
}
