package com.project.spider.service.base;

import java.io.Serializable;
import java.util.List;

import com.project.spider.mapper.BaseMapper;
/**
 * 
 * @author bjluzhangqing
 *
 * @param <T>
 * @param <ID>
 */

public interface BaseService <T,ID extends Serializable> {
	void setBaseMapper(BaseMapper<T, ID> baseMapper);
	List<T> selectAll();
	
}
