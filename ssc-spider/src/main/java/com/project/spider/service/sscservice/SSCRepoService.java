package com.project.spider.service.sscservice;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;

import com.project.spider.model.Row;
import com.project.spider.model.SSCRepo;
import com.project.spider.service.base.BaseService;

public interface SSCRepoService extends BaseService<SSCRepo, Serializable>{
	/**
	 * intodb
	 */
	public int insertSelective(SSCRepo sscRepo) throws DataAccessException;
	
	/**
	 * 选择最近插入的记录
	 * @param gameId
	 * @return
	 * @throws DataAccessException
	 */
	public Row selectRecentGameId(int gameId) throws DataAccessException;
}
