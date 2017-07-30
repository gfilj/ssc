package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.api.BaseVO;
import com.project.model.api.builder.LotteryVOBuilder;
import com.project.spider.mapper.SSCLotteryMapper;
import com.project.spider.model.Row;
/**
 * 
 * @author goforit
 *
 */
@Service
public class SSCLotteryService {
	@Autowired
	private SSCLotteryMapper sscLotteryMapper;
	
	public BaseVO onlineLotterys() {
		return LotteryVOBuilder.getInstance()
		.setCode(1).setMsg("ok").setResult(sscLotteryMapper.selectAll()).build();
	}
	
	
	public Row selectById(int id) {
		Row param = Row.getInstance();
		param.put("id", id);
		return sscLotteryMapper.selectByPrimaryKey(param);
	}
}
