package com.project.spider.mapper;

import java.io.Serializable;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;

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
	@Insert("replace into t_ssc_issue(issue,digit5,digit4,digit3,digit2,digit1,type) values(#{issue},#{digit5},#{digit4},#{digit3},#{digit2},#{digit1},#{type})")
	@Result(javaType = Integer.class)
	public int insertSelective(SSCRepo sscRepo);
}
