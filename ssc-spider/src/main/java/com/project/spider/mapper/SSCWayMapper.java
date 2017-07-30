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
public interface SSCWayMapper extends BaseMapper<Row,Row>{
	@Override
	@Insert("INSERT INTO `t_ssc_way` (`id`,`nameCn`,`nameEn`) VALUES (#{id}, #{nameCn}, #{nameEn})")
	@Result(javaType = Integer.class)
	int insertSelective(Row row) throws DataAccessException;
	
	@Override
	@Select("SELECT * FROM t_ssc_way where wayid = #{param.id}")
	@Result(javaType = Row.class)
	Row selectByPrimaryKey(@Param(value = "param") Row param) throws DataAccessException;
	
	@Override
	@Select("SELECT * FROM t_ssc_way")
	@Result(javaType = Row.class)
	List<Row> selectAll() throws DataAccessException;
	
}
