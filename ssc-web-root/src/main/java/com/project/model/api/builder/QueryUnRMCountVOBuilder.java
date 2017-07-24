package com.project.model.api.builder;

import com.project.model.api.ResponseVO;
/**
 * 获取消息数
 * @author goforit
 *
 */
public class QueryUnRMCountVOBuilder {
	
	private ResponseVO responseVO = new ResponseVO();

	public static QueryUnRMCountVOBuilder getInstance() {
		return new QueryUnRMCountVOBuilder();
	}

	public QueryUnRMCountVOBuilder setType(String type) {
		responseVO.setType(type);
		return this;
	}

	public QueryUnRMCountVOBuilder setIsSuccess(int isSuccess) {
		responseVO.setIsSuccess(isSuccess);
		return this;
	}
	
	public QueryUnRMCountVOBuilder setData(int data) {
		responseVO.setData(data);
		return this;
	}

	public ResponseVO build() {
		return responseVO;
	}
	
}
