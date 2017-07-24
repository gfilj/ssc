package com.project.model.api.builder;

import java.util.HashMap;
import java.util.Map;

import com.project.model.api.ResponseVO;

public class BetsVOBuilder {

	private ResponseVO responseVO = new ResponseVO();

	public static BetsVOBuilder getInstance() {
		return new BetsVOBuilder();
	}
	
	public BetsVOBuilder setType(String type) {
		responseVO.setType(type);
		return this;
	}

	public BetsVOBuilder setMsg(String msg) {
		responseVO.setMsg(msg);
		return this;
	}
	
	public BetsVOBuilder setIsSuccess(int isSuccess) {
		responseVO.setIsSuccess(isSuccess);
		return this;
	}

	public BetsVOBuilder setData(String msg, String money, String link) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("msg",msg);
		map.put("money",money);
		map.put("link",link);
		Map<String,Object> tplMap = new HashMap<String, Object>();
		tplMap.put("tplData",map);
		responseVO.setData(tplMap);
		return this;
	}

	public ResponseVO build() {
		return responseVO;
	}
	
}
