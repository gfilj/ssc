package com.project.spider.service.sscservice.impl;

import java.io.Serializable;
import java.util.List;

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
	
	private SSCRepoMapper sscRepoMapper;
	
	@Override
	@Autowired
	public void setBaseMapper(BaseMapper<SSCRepo, Serializable> sscRepoMapper) {
		setAbsBaseMapper(sscRepoMapper);
		this.sscRepoMapper = (SSCRepoMapper)sscRepoMapper;
		
	}

	@Override
	public int insertSelective(SSCRepo sscRepo) throws DataAccessException {
		int insertNum = 0;
		try {
			insertNum = sscRepoMapper.insertSelective(sscRepo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertNum;
	}

	@Override
	public Row selectRecentGameId(int gameId) throws DataAccessException{
		try {
			Row row = Row.getInstance();
			row.put("type", gameId);
			return sscRepoMapper.selectRecentGameId(row);
		} catch (Exception e) {
			e.printStackTrace(
					);
		}
		return null;
	}

	@Override
	public Row selectByID(String issue, int gameID) throws DataAccessException {
		try {
			Row row = Row.getInstance();
			row.put("issue",issue);
			row.put("type",gameID);
			return sscRepoMapper.selectByID(row);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateRollUp(String issue, int gameID) throws DataAccessException {
		try {
			Row row = Row.getInstance();
			row.put("issue",issue);
			row.put("type",gameID);
			sscRepoMapper.updateRollUp(row);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<Row> selectRecentList(int gameId, int size) throws DataAccessException {
		try {
			Row row = Row.getInstance();
			row.put("size",size);
			row.put("type",gameId);
			return sscRepoMapper.selectRecentList(row);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
