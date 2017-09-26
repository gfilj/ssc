package com.project.model.api.builder;
/**
 * 
 * @author goforit
 *
 */

import java.util.HashMap;
import java.util.Map;

import com.project.model.api.ResponseVO;
/**
 * 获取抽奖任务时间下一次抽奖的期数
 * @author goforit
 *
 */
public class LoadTimeVOBuilder {

	private ResponseVO responseVO = new ResponseVO();

	public static LoadTimeVOBuilder getInstance() {
		return new LoadTimeVOBuilder();
	}

	public LoadTimeVOBuilder setType(String type) {
		responseVO.setType(type);
		return this;
	}

	public LoadTimeVOBuilder setIsSuccess(int isSuccess) {
		responseVO.setIsSuccess(isSuccess);
		return this;
	}

	public LoadTimeVOBuilder setData(
			long currentTime,
			long currentIssueEndTime, 
			String currentIssueNumber,
			long nextIssueEndTime, 
			String nextIssueNumber) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("currentTime",currentTime);
		map.put("currentIssueEndTime",currentIssueEndTime);
		map.put("currentIssueNumber",currentIssueNumber);
		map.put("nextIssueEndTime",nextIssueEndTime);
		map.put("nextIssueNumber",nextIssueNumber);
		responseVO.setData(map);
		return this;
	}

	public ResponseVO build() {
		return responseVO;
	}
}
