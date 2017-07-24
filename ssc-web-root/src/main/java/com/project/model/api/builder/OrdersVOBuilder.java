package com.project.model.api.builder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.project.model.api.ResponseVO;
/**
 * 购买记录显示
 * @author goforit
 *
 */
public class OrdersVOBuilder {
	
	private ResponseVO responseVO = new ResponseVO();

	public static OrdersVOBuilder getInstance() {
		return new OrdersVOBuilder();
	}

	public OrdersVOBuilder setType(String type) {
		responseVO.setType(type);
		return this;
	}

	public OrdersVOBuilder setIsSuccess(int isSuccess) {
		responseVO.setIsSuccess(isSuccess);
		return this;
	}
	
	public OrdersVOBuilder setPage() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("currPage",1);
		map.put("currPage",10);
		map.put("totalItem",65);
		map.put("totalPage",7);
		List<Order> recordList = new LinkedList<Order>();
		recordList.add(new Order());
		map.put("list",recordList);
		responseVO.setPage(map);
		return this;
	}

	public ResponseVO build() {
		return responseVO;
	}
	
	public class Order {
		
		private String orderItemId = "20170703015vntOGOs0001";
        private String userName = "zx19926276";
        private int userId = 10961;
        private String orderTime = "2017-07-03 14:55:33";
        private String lottery = "重庆时时彩";
        private String method = "任选三直选单式万千个";
        private String issue = "20170703-054";
        private String code = "9,8,7|9,8,5|9,8,4|9,8,3|9,8,0|9,7,8|9,7,6|9,7,4|9,";
        private int count = 9;
        private double amount = 15.75;
        private int awardMoney = 0;
        private String winningNumber = "9,9,1,6,2";
        private int istrace = 0;
        private double perAmount = 0.01;
        private String state = "未中奖";
        private int status = 1;
        private String odds = "1956.0000";
        private int oddsMode = 1956;
        private int cancelStatus = 0;
        private int lotteryId = 1;
        private int nums = 175;
        private int point = 0;
        private boolean canCancel = false;
		public String getOrderItemId() {
			return orderItemId;
		}
		public void setOrderItemId(String orderItemId) {
			this.orderItemId = orderItemId;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getOrderTime() {
			return orderTime;
		}
		public void setOrderTime(String orderTime) {
			this.orderTime = orderTime;
		}
		public String getLottery() {
			return lottery;
		}
		public void setLottery(String lottery) {
			this.lottery = lottery;
		}
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public String getIssue() {
			return issue;
		}
		public void setIssue(String issue) {
			this.issue = issue;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public int getAwardMoney() {
			return awardMoney;
		}
		public void setAwardMoney(int awardMoney) {
			this.awardMoney = awardMoney;
		}
		public String getWinningNumber() {
			return winningNumber;
		}
		public void setWinningNumber(String winningNumber) {
			this.winningNumber = winningNumber;
		}
		public int getIstrace() {
			return istrace;
		}
		public void setIstrace(int istrace) {
			this.istrace = istrace;
		}
		public double getPerAmount() {
			return perAmount;
		}
		public void setPerAmount(double perAmount) {
			this.perAmount = perAmount;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getOdds() {
			return odds;
		}
		public void setOdds(String odds) {
			this.odds = odds;
		}
		public int getOddsMode() {
			return oddsMode;
		}
		public void setOddsMode(int oddsMode) {
			this.oddsMode = oddsMode;
		}
		public int getCancelStatus() {
			return cancelStatus;
		}
		public void setCancelStatus(int cancelStatus) {
			this.cancelStatus = cancelStatus;
		}
		public int getLotteryId() {
			return lotteryId;
		}
		public void setLotteryId(int lotteryId) {
			this.lotteryId = lotteryId;
		}
		public int getNums() {
			return nums;
		}
		public void setNums(int nums) {
			this.nums = nums;
		}
		public int getPoint() {
			return point;
		}
		public void setPoint(int point) {
			this.point = point;
		}
		public boolean isCanCancel() {
			return canCancel;
		}
		public void setCanCancel(boolean canCancel) {
			this.canCancel = canCancel;
		}
        
	}
	
}
