package com.project.spider.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.*;
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
			"`point`," +
			"`wayid`," +
			"`type`)" +
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
			"\"\"," +
			"0," + 
			"#{param.perAmount}," + 
			"#{param.state}," + 
			"#{param.status}," + 
			"#{param.oddsMode}," + 
			"#{param.odds}," + 
			"#{param.cancelStatus}," + 
			"true," + 
			"#{param.nums}," +
			"0.0," +
			"#{param.wayid}," +
			"#{param.type})")
	@Result(javaType = Integer.class)
	
	int insertSelective(@Param(value = "param")Row param) throws DataAccessException;
	
	
	@Override
	@Select("SELECT * FROM t_ssc_record")
	@Result(javaType=Row.class)
	List<Row> selectAll() throws DataAccessException;


	@Select("SELECT * from t_ssc_record where userName=#{param.userId}")
    @Result(javaType=Row.class)
	List<Row> selectByUserId(@Param(value = "param")Row param) throws DataAccessException;

	/**
	 * 更新中奖号码
	 * @param param
	 * 期数和类型
	 * @throws DataAccessException
	 */
	@Update("update t_ssc_record set winningNumber = #{param.winningNumber} where issue = #{param.issue} and lotteryId = #{param.lotteryId} ")
	void updateWinningNumber(@Param(value="param")Row param) throws DataAccessException;

	/**
	 * 更新状态
	 * @param row
	 * 订单号
	 */
	@Update("update t_ssc_record set canCancel = false where orderItemId = #{param.orderItemId}")
	void updateCanCancle(@Param(value="param")Row row) throws DataAccessException;

	/**
	 * 选择某一类型下对应的期号的所有订单
	 */
    @Select("SELECT * FROM t_ssc_record where issue = #{param.issue} and lotteryId =#{param.lotteryId};")
    @Result(javaType=Row.class)
	List<Row> selectOrdersByIssue(@Param(value="param") Row row) throws DataAccessException;
}
