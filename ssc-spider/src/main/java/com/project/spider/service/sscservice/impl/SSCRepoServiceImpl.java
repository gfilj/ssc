package com.project.spider.service.sscservice.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.spider.mapper.BaseMapper;
import com.project.spider.mapper.SSCRepoMapper;
import com.project.spider.model.Row;
import com.project.spider.model.SSCRepo;
import com.project.spider.service.base.impl.AbsBaseServiceImpl;
import com.project.spider.service.sscservice.SSCRepoService;

@Service("sscService")
public class SSCRepoServiceImpl extends AbsBaseServiceImpl<SSCRepo, Serializable> implements SSCRepoService{
	
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

	@Override
	public Row selectRecentGameId(int gameId) throws DataAccessException{
		try {
			Row row = Row.getInstance();
			row.put("gameId", gameId);
			return sSCRepoMapper.selectRecentGameId(row);
		} catch (Exception e) {
			e.printStackTrace(
					);
		}
		return null;
	}

}
