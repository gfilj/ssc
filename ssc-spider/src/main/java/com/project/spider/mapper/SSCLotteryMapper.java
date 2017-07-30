package com.project.spider.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.dao.DataAccessException;

import com.project.spider.model.Row;
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
	
}
