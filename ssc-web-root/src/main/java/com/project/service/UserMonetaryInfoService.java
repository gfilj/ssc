package com.project.service;

import org.springframework.stereotype.Service;

import com.project.model.api.ResponseVO;
import com.project.model.api.builder.UserMonetaryInfoVOBuilder;

@Service
public class UserMonetaryInfoService {
	public ResponseVO userMonetaryInfo() {
		return UserMonetaryInfoVOBuilder.getInstance()
		.setIsSuccess(1)
		.setType("info")
		.setData("8.1083", "zx19926276", "0.0").build();
	}
}
