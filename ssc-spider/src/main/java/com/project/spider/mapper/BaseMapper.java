package com.project.spider.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface BaseMapper<T,ID extends Serializable> {
	public int insertSelective(T obj) throws DataAccessException;
	public int deleteByPrimaryKey(ID id) throws DataAccessException;
	public int updateByPrimaryKeySelective(T obj) throws DataAccessException;
	public T selectByPrimaryKey(ID id) throws DataAccessException;
	public List<T> selectAll() throws DataAccessException;
}