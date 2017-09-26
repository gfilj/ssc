package com.project.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.project.model.api.ResponseVO;
import com.project.model.api.builder.BetsVOBuilder;
import com.project.spider.mapper.SSCLotteryMapper;
import com.project.spider.mapper.SSCRecordMapper;
import com.project.spider.mapper.SSCWayMapper;
import com.project.spider.model.Row;

@Service
public class SSCBetsService {
	
	@Autowired
	private SSCRecordService sscRecordService;

	@Autowired
	private SSCLotteryService sscLotteryService;

	@Autowired
	private SSCWayService sscWayService;

	private static Logger logger = Logger.getLogger("bet");
	public ResponseVO betsResult() {
		return BetsVOBuilder.getInstance().setIsSuccess(1).setMsg("投注成功").setType("success")
				.setData("投注成功", "4.1083", "/hz/mkg/orders.html").build();
	}

	public int insertSelective(Map<String, Object> param) {
		int insertNum = 0;
		List<Row> ballList = JSON.parseArray((String) param.get("ballstr"), Row.class);
		logger.info("orderStr:" + param);
		for (Row ball : ballList) {
			try {
				Row recordItem = Row.getInstance();
				int wayId = Integer.parseInt(ball.getString("wayId"));
				recordItem.put("wayid", wayId);
				recordItem.put("type", ball.getString("type"));
				Row wayIdRow = sscWayService.selectById(wayId);
				System.out.println(wayIdRow);
				recordItem.put("method",  wayIdRow.getString("nameCn"));
				int gameId = Integer.parseInt((String)param.get("gameId"));
				recordItem.put("lotteryId", gameId);
				recordItem.put("lottery", sscLotteryService.selectById(gameId).getString("cn"));
				Date orderTimeDate = new Date();
				recordItem.put("orderItemId", String.valueOf(orderTimeDate.getTime()));
				//TODO cookie
				recordItem.put("userId",1);
				recordItem.put("userName","zx19926276");
				
				recordItem.put("code", ball.getString("ball"));
				recordItem.put("orderTime", orderTimeDate);
				recordItem.put("nums", ball.getInt("num"));
				recordItem.put("perAmount", ball.getFloat("moneyunit"));
				recordItem.put("count", ball.getInt("multiple"));
				recordItem.put("amount", ball.getInt("num") * ball.getInt("multiple") * ball.getFloat("moneyunit"));
				recordItem.put("oddsMode", ball.getInt("prizeGroup"));
				JSONObject issumeObject = JSON.parseObject((String) param.get("orderstr"));
				recordItem.put("issue", issumeObject.keySet().iterator().next());


				System.out.println(recordItem);

				insertNum += sscRecordService.addOrders(recordItem);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
		}
		return insertNum;
	}
}
