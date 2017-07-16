package com.project.spider.service.base.impl;

import java.io.Serializable;
import java.util.List;

import com.project.spider.mapper.BaseMapper;
import com.project.spider.service.base.BaseService;


public abstract class AbsBaseServiceImpl <T, ID extends Serializable> implements BaseService<T, ID>{

	private BaseMapper<T, ID> baseMapper;
	
	
	public void setAbsBaseMapper(BaseMapper<T, ID> baseMapper) {
		this.baseMapper = baseMapper;
	}
	
	@Override
	public List<T> selectAll() {
		return baseMapper.selectAll();
	}

}
