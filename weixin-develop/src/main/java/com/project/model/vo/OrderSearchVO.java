package com.project.model.vo;

/**
 * 订单查询
 */
public class OrderSearchVO {
    private String realname;//姓名

    private String tel;//电话手机号
    private String mark;//备注
    private String address;//送货地址
    private String productnumber;//商品编号
    private String productname;//品名
    private String jingdname;//电商
    private String quantity;//数量

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getJingdname() {
        return jingdname;
    }

    public void setJingdname(String jingdname) {
        this.jingdname = jingdname;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
