package com.project.spider.mapper;

import java.io.Serializable;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import com.project.spider.model.Row;
import com.project.spider.model.SSCRepo;
/**
 * 
 * @author bjluzhangqing
 *
 */
@Mapper
public interface SSCRepoMapper extends BaseMapper<SSCRepo,Serializable>{
	/**
	 * intoDB
	 * @param sscRepo
	 */
	@Override
	@Insert("replace into t_ssc_issue(issue,digit5,digit4,digit3,digit2,digit1,type) values(#{issue},#{digit5},#{digit4},#{digit3},#{digit2},#{digit1},#{type})")
	@Result(javaType = Integer.class)
	int insertSelective(SSCRepo sscRepo) throws DataAccessException;
	
	@Select("SELECT * FROM t_ssc_issue where type = #{param.gameId} order by time desc limit 1")
	@Result(javaType = Row.class)
	Row selectRecentGameId(@Param(value = "param")Row param);
	
}
