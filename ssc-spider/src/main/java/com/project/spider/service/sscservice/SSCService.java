package com.project.spider.service.sscservice;

import java.io.Serializable;

import org.springframework.dao.DataAccessException;

import com.project.spider.model.SSCRepo;
import com.project.spider.service.base.BaseService;

public interface SSCService extends BaseService<SSCRepo, Serializable>{
	/**
	 * intodb
	 */
	public int insertSelective(SSCRepo sscRepo) throws DataAccessException;
}
