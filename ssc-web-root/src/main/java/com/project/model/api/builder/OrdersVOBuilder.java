package com.project.model.api.builder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.project.model.api.ResponseVO;
import com.project.spider.model.Row;
/**
 * 购买记录显示
 * @author goforit
 *
 */
public class OrdersVOBuilder {
	
	private ResponseVO responseVO = new ResponseVO();

	public static OrdersVOBuilder getInstance() {
		return new OrdersVOBuilder();
	}

	public OrdersVOBuilder setType(String type) {
		responseVO.setType(type);
		return this;
	}

	public OrdersVOBuilder setIsSuccess(int isSuccess) {
		responseVO.setIsSuccess(isSuccess);
		return this;
	}
	
	public OrdersVOBuilder setPage(List<Row> recordList) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("currPage",1);
		map.put("currPage",10);
		map.put("totalItem",65);
		map.put("totalPage",7);
		map.put("list",recordList);
		responseVO.setPage(map);
		return this;
	}

	public ResponseVO build() {
		return responseVO;
	}
	
}