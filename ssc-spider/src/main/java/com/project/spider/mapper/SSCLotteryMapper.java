package com.project.spider.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.springframework.dao.DataAccessException;

import com.project.spider.model.Row;

import javax.xml.crypto.Data;

/**
 * @Mapper
 * @author goforit
 *
 */
@Mapper
public interface SSCLotteryMapper extends BaseMapper<Row,Row>{
	@Override
	@Insert("INSERT INTO `t_ssc_lottery` (`id`,`code`,`cn`) VALUES (#{param.id}, #{param.code}, #{param.cn})")
	@Result(javaType = Integer.class)
	int insertSelective(@Param(value = "param") Row row) throws DataAccessException;
	
	@Override
	@Select("SELECT * FROM t_ssc_lottery where id = #{param.id}")
	@Result(javaType = Row.class)
	Row selectByPrimaryKey(@Param(value = "param")Row param) throws DataAccessException;
	
	@Override
	@Select("SELECT * FROM t_ssc_lottery ")
	@Result(javaType = Row.class)
	List<Row> selectAll() throws DataAccessException;

	/**
	 * 更新期号为开奖状态
	 */
	@Update("update t_ssc_issue set rollout=true where issue=#{param.issue} and type = #{param.type}")
	int unpdateStatus(@Param(value = "param")Row param) throws DataAccessException;
	
}
