package com.project.spider.service.sscservice;

import com.project.spider.model.Row;
import com.project.spider.model.SSCRepo;
import com.project.spider.service.base.BaseService;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;

public interface SSCRepoService extends BaseService<SSCRepo, Serializable>{
	/**
	 * innodb
	 */
	int insertSelective(SSCRepo sscRepo) throws DataAccessException;
	
	/**
	 * 选择最近插入的记录
	 * @param gameId
	 * @return
	 * @throws DataAccessException
	 */
	Row selectRecentGameId(int gameId) throws DataAccessException;

	/**
	 * 根据期数和类型选择出对应的玩法
	 * @param
	 * @return
	 * @throws DataAccessException
	 */
	Row selectByID(String issue, int gameID) throws DataAccessException;


	/**
	 * 更新为开奖状态
	 * @param issue
	 * @param gameID
	 * @throws DataAccessException
	 */
	void updateRollUp(String issue,int gameID) throws DataAccessException;


	/**
	 *  选择显示最近的列表
	 * @param gameId
	 * @return
	 * @throws DataAccessException
	 */
	List<Row> selectRecentList(int gameId, int size)throws DataAccessException;
}
