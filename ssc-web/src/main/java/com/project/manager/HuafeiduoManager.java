package com.project.manager;


/**
 * 话费多
 */
public interface HuafeiduoManager {
	
	/**
	 * 查询账户余额
	 */
	public void getAccountBalance();
	
	/**
	 * 检查手机号是否能下单充值，并获取下单(进货)价格，以及手机号运营商、归属地等相关信息
	 * @param phoneNumber				手机号
	 * @param cardWorth						充值面额
	 */
	public String orderPhoneCheck(String phoneNumber, String cardWorth);
	
	/**
	 * 提交充值订单
	 * @param phoneNumber				手机号
	 * @param cardWorth						充值面额
	 * @param spOrderId						商户唯一订单号
	 */
	public String orderPhoneSubmit(String phoneNumber, String cardWorth, String spOrderId);

	/**
	 * 查询充值订单状态
	 * @param spOrderId						商户唯一订单号
	 */
	public String orderPhoneStatus(String spOrderId);
	
	
	
}
