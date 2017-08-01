package com.project.service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.api.ResponseVO;
import com.project.model.api.builder.LoadTimeVOBuilder;
import com.project.spider.model.Row;
import com.project.spider.service.sscservice.SSCRepoService;
import com.project.spider.task.SSCScheduleTask;
import com.project.util.DateUtils;

@Service
public class SSCIssueService {
	@Autowired
	private SSCRepoService sscRepoService;
	
	@Autowired
	private SSCLotteryService sscLotteryService;
	
	@Autowired
	private SSCScheduleTask sscScheduleTask;
	
	public ResponseVO loadtime(int gameId) {
		//选择文档中更新的时间
		Row recentIssue = sscRepoService.selectRecentGameId(gameId);
		Date issueTime = (Date)recentIssue.get("time");
		String issue = (String)recentIssue.get("issue");
		int issueDay = Integer.parseInt(issue.substring(0,issue.length()-3));
		int issueSub = Integer.parseInt(issue.substring(issue.length()-3));
		//选择gameId的配置
		Row lottery = sscLotteryService.selectById(gameId);
		String begin_time = lottery.getString("begin_time");
		String end_time = lottery.getString("end_time");
		String change_time = lottery.getString("change_time");
		int big_interval = lottery.getInt("big_interval");
		int small_interval = lottery.getInt("small_interval");
		Date beginTime = DateUtils.getSpecify(begin_time);
		Date endTime = DateUtils.getSpecify(end_time);
		Date changeTime = DateUtils.getSpecify(change_time);
		
		long currentIssueEndTime = 0;
		int dateInterval = 0;
		
		if(issueTime.before(beginTime)) {
			if(issueTime.after(endTime)) {
				dateInterval = big_interval*60;
				currentIssueEndTime = DateUtils.getDateUnixTimeStamp(beginTime);
			}else {
				dateInterval = small_interval*60;
				long useDateInterval = DateUtils.getDateInterval(issueTime, new Date());
				if(useDateInterval < dateInterval) {
					currentIssueEndTime = DateUtils.getSkipUnixTimeStamp(issueTime,Calendar.SECOND, dateInterval);
				}else{
					//重新下发进行抓取
					sscScheduleTask.per5MinuteTask();
					currentIssueEndTime = DateUtils.getSkipUnixTimeStamp(issueTime,Calendar.SECOND, dateInterval+2);
				}
			}
		}else {
			if(issueTime.before(changeTime)) {
				dateInterval = big_interval*60;
			}else {
				dateInterval = small_interval*60;
			}
			long useDateInterval = DateUtils.getDateInterval(issueTime, new Date());
			if(useDateInterval < dateInterval) {
				currentIssueEndTime = DateUtils.getSkipUnixTimeStamp(issueTime,Calendar.SECOND, dateInterval);
			}else{
				//重新下发进行抓取
				sscScheduleTask.per5MinuteTask();
				currentIssueEndTime = DateUtils.getSkipUnixTimeStamp(issueTime,Calendar.SECOND, dateInterval+2);
			}
		}
		
		if(issueSub+1>120) {
			issueDay +=1;
			issueSub =1;
		}else {
			issueSub +=1;
		}
		String nextIssue = String.valueOf(issueDay) + new DecimalFormat("000").format(issueSub);
		return LoadTimeVOBuilder.getInstance()
		.setIsSuccess(1)
		.setType("info")
		.setData(DateUtils.getUnixNowTimeStamp(),
				currentIssueEndTime, 
				issue, 
				currentIssueEndTime + dateInterval, 
				nextIssue).build();
	}
	
	/**
	 * 当前是哪一期
	 * @return
	 */
	public static int getIssueSub(){
		return (DateUtils.getHour()-6)*6 + DateUtils.getMin()/10 + 1;
	}
	
	/**
	 * 当前这一期的结束时间
	 */
	public static long getIssueEndUnixTimeStamp(Date beginTime){
		int issumeSub = getIssueSub();
		if(issumeSub<72){
			return DateUtils.getSkipUnixTimeStamp(beginTime, Calendar.MINUTE, issumeSub*10);
		}
	}
	
	
}
