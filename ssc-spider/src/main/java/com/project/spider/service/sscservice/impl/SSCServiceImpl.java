package com.project.spider.service.sscservice.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.spider.mapper.BaseMapper;
import com.project.spider.mapper.SSCRepoMapper;
import com.project.spider.model.SSCRepo;
import com.project.spider.service.base.impl.AbsBaseServiceImpl;
import com.project.spider.service.sscservice.SSCService;

@Service("sSCService")
public class SSCServiceImpl extends AbsBaseServiceImpl<SSCRepo, Serializable> implements SSCService{
	
	private SSCRepoMapper sSCRepoMapper;
	
	@Override
	@Autowired
	public void setBaseMapper(BaseMapper<SSCRepo, Serializable> sSCRepoMapper) {
		setAbsBaseMapper(sSCRepoMapper);
		this.sSCRepoMapper = (SSCRepoMapper)sSCRepoMapper;
		
	}

	@Override
	public int insertSelective(SSCRepo sscRepo) throws DataAccessException {
		int insertNum = 0;
		try {
			insertNum = sSCRepoMapper.insertSelective(sscRepo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertNum;
	}

}
