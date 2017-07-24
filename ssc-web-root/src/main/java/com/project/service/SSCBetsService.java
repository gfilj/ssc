package com.project.service;

import org.springframework.stereotype.Service;

import com.project.model.api.ResponseVO;
import com.project.model.api.builder.BetsVOBuilder;

@Service
public class SSCBetsService {
	
	public ResponseVO betsResult() {
		return BetsVOBuilder.getInstance().
				setIsSuccess(1).
				setMsg("投注成功").
				setType("success").
				setData("投注成功", "4.1083", "/hz/mkg/orders.html").build();
	}
}
