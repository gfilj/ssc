package com.project.service;

import org.springframework.stereotype.Service;

import com.project.model.api.BaseVO;
import com.project.model.api.builder.LotteryVOBuilder;
/**
 * 
 * @author goforit
 *
 */
@Service
public class SSCLotteryService {
	public BaseVO onlineLotterys() {
		return LotteryVOBuilder.getInstance()
		.setCode(1).setMsg("ok").setResult().build();
	}
}
