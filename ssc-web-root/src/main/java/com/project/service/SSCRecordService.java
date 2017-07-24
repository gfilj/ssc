package com.project.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.project.model.api.BaseVO;
import com.project.model.api.ResponseVO;
import com.project.model.api.builder.LoadTimeVOBuilder;
import com.project.model.api.builder.LotteryVOBuilder;
import com.project.model.api.builder.OrdersVOBuilder;
import com.project.util.DateUtils;

/**
 * 
 * @author goforit
 *
 */
@Service
public class SSCRecordService {
	/**
	 * 查询历史记录
	 * 
	 * @return
	 */
	public ResponseVO queryOrders() {
		return OrdersVOBuilder.getInstance().setIsSuccess(1).setType("info").setPage().build();
	}
	/**
	 * 订单取消
	 */
	public BaseVO orderCancle() {
		return LotteryVOBuilder.getInstance().setCode(0).setMsg("撤单成功").build();
	}
	
	
}
