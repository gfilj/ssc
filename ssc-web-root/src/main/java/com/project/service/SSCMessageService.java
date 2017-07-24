package com.project.service;

import org.springframework.stereotype.Service;

import com.project.model.api.ResponseVO;
import com.project.model.api.builder.QueryUnRMCountVOBuilder;
@Service
public class SSCMessageService {
	
	public ResponseVO queryUnRMCount() {
		return QueryUnRMCountVOBuilder.getInstance().setData(0).setIsSuccess(1).setType("info").build();
	}
}
