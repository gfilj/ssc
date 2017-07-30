package com.project.spider.mapper;

import java.io.Serializable;
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
public interface SSCRecordMapper extends BaseMapper<Row,Serializable>{
	/**
	 * intoDB
	 * @param sscRepo
	 */
	@Override
	@Insert("INSERT INTO `t_ssc_record`" + 
			"(`orderItemId`," + 
			"`userName`," +
			"`userId`," +
			"`orderTime`," +
			"`lottery`," +
			"`lotteryId`," + 
			"`method`," + 
			"`issue`," + 
			"`code`," + 
			"`count`," + 
			"`amount`," + 
			"`awardMoney`," + 
			"`winningNumber`," + 
			"`istrace`," + 
			"`perAmount`," + 
			"`state`," + 
			"`status`," + 
			"`oddsMode`," + 
			"`odds`," + 
			"`cancelStatus`," + 
			"`canCancel`," + 
			"`nums`," + 
			"`point`)" + 
			"VALUES" + 
			"(#{param.orderItemId}," + 
			"#{param.userName}," + 
			"#{param.userId}," + 
			"#{param.orderTime}," + 
			"#{param.lottery}," + 
			"#{param.lotteryId}," + 
			"#{param.method}," + 
			"#{param.issue}," + 
			"#{param.code}," + 
			"#{param.count}," + 
			"#{param.amount}," + 
			"#{param.awardMoney}," + 
			"#{param.winningNumber}," + 
			"0," + 
			"#{param.perAmount}," + 
			"#{param.state}," + 
			"#{param.status}," + 
			"#{param.oddsMode}," + 
			"#{param.odds}," + 
			"#{param.cancelStatus}," + 
			"true," + 
			"#{param.nums}," + 
			"0.0)")
	@Result(javaType = Integer.class)
	
	int insertSelective(@Param(value = "param")Row param) throws DataAccessException;
	
	
	@Override
	@Select("SELECT * FROM t_ssc_record")
	@Result(javaType=Row.class)
	List<Row> selectAll() throws DataAccessException;
	
	@Select("SELECT * from t_ssc_record where userName=#{param.userId}")
	List<Row> selectByUserId(@Param(value = "param")Row param) throws DataAccessException;
}
