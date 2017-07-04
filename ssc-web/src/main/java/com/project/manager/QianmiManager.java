package com.project.manager;

public interface QianmiManager {

    /**
     * 获取需要充值的订单接口
     */
    public void supply(String reqId);

    public void checkOrder(String orderIds, String reqId);

    /**
     * 订单充值前确认该笔订单是否可以充值
     */
    public void confirmRecharge(String orderId, String id);

    /**
     * 充值完成后将充值结果返回给千米，此操作只允许成功操作一次。
     */
    public void setOrder(String orderId, String id, String OrderState);




}
