package com.project.model.api.builder;

import java.util.HashMap;
import java.util.Map;

import com.project.model.api.ResponseVO;
/**
 * 获取用户和当前的剩余总额
 * @author goforit
 *
 */
public class UserMonetaryInfoVOBuilder {
	
	private ResponseVO responseVO = new ResponseVO();

	public static UserMonetaryInfoVOBuilder getInstance() {
		return new UserMonetaryInfoVOBuilder();
	}

	public UserMonetaryInfoVOBuilder setType(String type) {
		responseVO.setType(type);
		return this;
	}

	public UserMonetaryInfoVOBuilder setIsSuccess(int isSuccess) {
		responseVO.setIsSuccess(isSuccess);
		return this;
	}
	
	public UserMonetaryInfoVOBuilder setData(String available, 
			String userName, 
			String currentDayProfit) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("available",available);
		map.put("userName",userName);
		map.put("currentDayProfit",currentDayProfit);
		responseVO.setData(map);
		return this;
	}

	public ResponseVO build() {
		return responseVO;
	}
	
}
