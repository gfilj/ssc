package com.project.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.project.model.api.ResponseVO;
import com.project.model.api.builder.LoadTimeVOBuilder;
import com.project.util.DateUtils;

@Service
public class SSCIssueService {
	private int time = 600, nextTime = 1200;
	public ResponseVO loadtime() {
		return LoadTimeVOBuilder.getInstance()
		.setIsSuccess(1)
		.setType("info")
		.setData(DateUtils.getUnixNowTimeStamp(),
				DateUtils.getUnixNowTimeStamp(Calendar.SECOND, time--), 
				"20170722-027", 
				DateUtils.getUnixNowTimeStamp(Calendar.SECOND, nextTime--), 
				"20170722-028").build();
	}
}
