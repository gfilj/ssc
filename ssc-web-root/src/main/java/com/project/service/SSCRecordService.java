package com.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.api.BaseVO;
import com.project.model.api.ResponseVO;
import com.project.model.api.builder.LotteryVOBuilder;
import com.project.model.api.builder.OrdersVOBuilder;
import com.project.spider.mapper.SSCRecordMapper;
import com.project.spider.model.Row;

/**
 * 
 * @author goforit
 *
 */
@Service
public class SSCRecordService {
	
	@Autowired
	private SSCRecordMapper sSCRecordMapper;
	
	public int addOrders(Row param) {
		try {
			return sSCRecordMapper.insertSelective(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 查询历史记录
	 * 
	 * @return
	 */
	public ResponseVO queryOrders() {
		try {
			
			return OrdersVOBuilder.getInstance().setIsSuccess(1).setType("info").setPage(sSCRecordMapper.selectAll()).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 订单取消
	 */
	public BaseVO orderCancle() {
		return LotteryVOBuilder.getInstance().setCode(0).setMsg("撤单成功").build();
	}

	/**
	 * 根据期号选择订单
	 */
	public List<Row> selectOrdersByIssue(String issue, int lotteryId){
		try{
		    Row row = Row.getInstance();
		    row.put("issue",issue);
		    row.put("lotteryId",lotteryId);

		    return sSCRecordMapper.selectOrdersByIssue(row);
        }catch (Exception e){
		    e.printStackTrace();
        }
        return null;
	}
}
