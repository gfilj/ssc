package com.project.spider.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.*;
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
	
	@Select("SELECT * FROM t_ssc_issue where type = #{param.type} order by issue desc limit 1")
	@Result(javaType = Row.class)
	Row selectRecentGameId(@Param(value = "param")Row param);


	@Select("SELECT * FROM t_ssc_issue where type = #{param.type} order by issue desc limit #{param.size}")
	@Result(javaType = Row.class)
	List<Row> selectRecentList(@Param(value = "param")Row param);


	@Select("SELECT * FROM t_ssc_issue where issue = #{param.issue} and type = #{param.type}")
	@Result(javaType = Row.class)
	Row selectByID(@Param(value = "param")Row param);

	/**
	 * 更新rollout字段
	 */
	@Update("update t_ssc_issue set rollout=1 where issue=#{param.issue} and type = #{param.type}")
	int updateRollUp(@Param(value = "param")Row row) throws DataAccessException;
}
