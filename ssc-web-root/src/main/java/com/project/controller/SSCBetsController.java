package com.project.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.model.Row;
import com.project.model.api.BaseVO;
import com.project.model.api.ResponseVO;
import com.project.service.LoadTimeService;
import com.project.service.SSCBetsService;
import com.project.service.SSCRecordService;
import com.project.service.UserMonetaryInfoService;

/**
 * 
 * @author goforit
 *
 */
@Controller
@RequestMapping("/hz")
public class SSCBetsController {
	
	@Autowired
	private SSCBetsService sSCBetsService;
	
	@RequestMapping(method = RequestMethod.POST,value="mkg/api/bets/bet")
    public @ResponseBody ResponseVO bets(@RequestParam Row param) {
		System.out.println(param);
		@SuppressWarnings("unchecked")
		List<Row> ballList = (List<Row>)param.get("ballstr");
		for(Row ball: ballList){
			Row recordItem = Row.getInstance();
			recordItem.put("wayId",param.getString("wayId"));
			recordItem.put("lotteryId",param.getString("gameId"));
			recordItem.put("orderItemId",String.valueOf(System.currentTimeMillis()));
			recordItem.put("userId",param.getString("userId"));
			recordItem.put("code",ball.getString("ball"));
			recordItem.put("orderTime",new Date());
			recordItem.put("nums",ball.getInt("num"));
			recordItem.put("perAmount",ball.getFloat("moneyunit"));
			recordItem.put("count",ball.getInt("multiple"));
			recordItem.put("amount", ball.getInt("num")*ball.getInt("multiple")*ball.getFloat("moneyunit"));
			recordItem.put("oddsMode", ball.getInt("prizeGroup"));
			recordItem.put("issue", ball.getRow("orderstr").getKey(0));
		}
        return sSCBetsService.betsResult();
    }
	
}
