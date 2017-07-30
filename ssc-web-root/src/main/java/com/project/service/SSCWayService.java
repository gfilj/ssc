package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.spider.mapper.SSCWayMapper;
import com.project.spider.model.Row;

@Service
public class SSCWayService {
	
	@Autowired
	private SSCWayMapper sscWayMapper;
	
	public Row selectById(int id) {
		Row param = Row.getInstance();
		param.put("id", id);
		return sscWayMapper.selectByPrimaryKey(param);
	} 
}