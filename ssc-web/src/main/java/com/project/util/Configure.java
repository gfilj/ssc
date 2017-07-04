package com.project.util;

public class Configure {

	
	public static class Weixin {
		
	}
	
	/**
	 * 充话费渠道话费多
	 *
	 */
	public static class huafeiduo {
		private static final String API_KEY = "";
		private static final String SECRET_KEY = "";
		// 查询账户余额
		private static final String ACCOUNT_BALANCE = "http://api.huafeiduo.com/gateway.cgi?mod=account.balance?api_key=%s&sign=%s";
		
		// 检查手机号是否能下单充值，并获取下单(进货)价格，以及手机号运营商、归属地等相关信息
		private static final String ORDER_PHONE_CHECK = "http://api.huafeiduo.com/gateway.cgi?mod=order.phone.check?phone_number=%s&card_worth=%s&api_key=%s&sign=%s";
		
		// 提交充值订单
		private static final String ORDER_PHONE_SUBMIT = "http://api.huafeiduo.com/gateway.cgi?mod=order.phone.submit?phone_number=%s&card_worth=%s&sp_order_id=%s&notify_url=%s&api_key=%s&sign=%s";
		
		// 查询充值订单状态
		private static final String ORDER_PHONE_STATUS = "http://api.huafeiduo.com/gateway.cgi?mod=order.phone.status?sp_order_id=%s&api_key=%s&sign=%s";
		
	}
	
}
