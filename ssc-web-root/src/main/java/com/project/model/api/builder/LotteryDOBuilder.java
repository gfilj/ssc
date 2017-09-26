package com.project.model.api.builder;
/**
 *
 * @author goforit
 *
 */
import com.project.util.DateUtils.*;
import com.project.spider.model.Row;
import com.project.util.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import static com.project.util.DateUtils.clearSeconds;
import static com.project.util.DateUtils.skipMin;

/**
 *
 * @author goforit
 *
 */
public class LotteryDOBuilder {

	public static LotteryDOBuilder getInstance() {
		return new LotteryDOBuilder();
	}

	private Row row;

	public LotteryDOBuilder setRow(Row row){
		this.row = row;
		return this;
	}

	public LotteryDOBuilder setBeginDate()  {
		row.put("begin_time_date",DateUtils.getSpecify(row.getString("begin_time")));
		return this;
	}

	public LotteryDOBuilder setEndDate() {
		row.put("end_time_date",DateUtils.getSpecify(row.getString("end_time")));
		return this;
	}

	public LotteryDOBuilder setChangeDate() {
		row.put("change_time_date",DateUtils.getSpecify(row.getString("change_time")));
		return this;
	}

	public Date getBeginDate(){
		return  row.getDate("begin_time_date");
	}
	public Date getEndDate(){
		return  row.getDate("end_time_date");
	}
	public Date getChangeDate(){
		return  row.getDate("change_time_date");
	}

	public DateTime getBeginDateTime(){
		return  row.getDateTime("begin_time_date");
	}
	public DateTime getEndDateTime(){
		return  row.getDateTime("end_time_date");
	}
	public DateTime getChangeDateTime(){
		return  row.getDateTime("change_time_date");
	}

	public int getBigInterval(){
		return row.getInt("big_interval");
	}

	public int getSmallInterval(){
		return row.getInt("small_interval");
	}

	public int getTotalNum(){
		return row.getInt("total_num");
	}

	public LotteryDOBuilder setSSCIssueLoad(){
            String currentIssueNumber;
            String nextIssueNumber;
            String issueDay= String.valueOf(DateUtils.getYYYYMMDD());
            int issueSub = 0;
            Date currentIssueEndTime;
            Date nextIssueEndTime;
            int interval = -1;//下一时段的时段间隔；


            if(getEndDate().before(getBeginDate())) {
                /**
                 * 计算出每个时间段的满值
                 */
                int beginMinutes = Minutes.minutesBetween(DateUtils.getJodaTime(DateUtils.getMorning()), getEndDateTime()).getMinutes();
                int middelMinutes = Minutes.minutesBetween(getBeginDateTime(), getChangeDateTime()).getMinutes();
                int currMinutes = Minutes.minutesBetween(DateUtils.getJodaTime(DateUtils.getMorning()), new DateTime()).getMinutes();
                if (DateUtils.getCurrDate().before(getEndDate())) {
                    interval = getSmallInterval();
                    //这才是开始
                    issueSub = currMinutes/interval + 1;
                } else if (DateUtils.getCurrDate().before(getBeginDate())) {
                    interval = -1;
                    //加上之前的凌晨的消息
                    issueSub = beginMinutes/getSmallInterval() + 1;
                } else if (DateUtils.getCurrDate().before(getChangeDate())) {
                    interval = getBigInterval();
                    //刚开始
                    issueSub = beginMinutes/getSmallInterval() + 1
                            + Minutes.minutesBetween(getBeginDateTime(), new DateTime()).getMinutes()/interval + 1;
                } else {
                    interval = getSmallInterval();
                    //加上changeTime 之前的
                    issueSub = beginMinutes/getSmallInterval() + 1 + middelMinutes/getBigInterval() +
                            + Minutes.minutesBetween(getChangeDateTime(), new DateTime()).getMinutes()/getSmallInterval() + 1;
                }
            }else{
                if (DateUtils.getCurrDate().before(getBeginDate())) {
                    interval = -1;
                    //未开始
                    issueSub = 1;
                } else if (DateUtils.getCurrDate().before(getChangeDate())) {
                    interval = getBigInterval();
                    //刚开始
                    issueSub = (DateUtils.getHour() - DateUtils.getHour(getBeginDate())) * DateUtils.HOUR_MINUTE / getBigInterval()
                            + DateUtils.getMin() / getBigInterval() + 1;
                } else if (DateUtils.getCurrDate().before(getEndDate())){
                    interval = getSmallInterval();
                    //加上changeTime 之前的
                    issueSub = (DateUtils.getHour(getChangeDate()) - DateUtils.getHour(getBeginDate())) * DateUtils.HOUR_MINUTE / getBigInterval()
                            + (DateUtils.getHour() - DateUtils.getHour(getChangeDate())) * DateUtils.HOUR_MINUTE / getSmallInterval()
                            + DateUtils.getMin() / getSmallInterval() + 1;
                }

            }

            /**
             * 计算上一次的期数
             */
            //上一期的时间
            String previousIssueNumer = "";
            int previousIssueSub = 0;
            String previousIssueDay = "";
            Date previousIssueEndTime = new Date();

            if(issueSub==1){
                previousIssueSub = getTotalNum();
                previousIssueDay = String.valueOf(DateUtils.getYYYYMMDD(DateUtils.skipDay(-1)));
            }else{
                previousIssueSub = issueSub - 1;
                previousIssueDay = issueDay;
            }
            previousIssueNumer = previousIssueDay + new DecimalFormat("000").format(previousIssueSub);

            /**
             * 计算下一期的期数
             */
            String nextIssueDay = "";
            int nextIssueSub = 0;
            if(issueSub >= getTotalNum()){
                nextIssueDay = String.valueOf(DateUtils.getYYYYMMDD(DateUtils.skip(Calendar.DAY_OF_YEAR,1)));
                nextIssueSub = 1;
    //            nextIssueEndTime = DateUtils.skipDay(1,getBeginDate());
            }else{
                nextIssueSub = issueSub + 1;
                nextIssueDay = issueDay;
                //如果在chang_time之前
    //            if(nextIssueSub<(DateUtils.getHourInterval(getChangeDate(), getBeginDate())) * DateUtils.HOUR_MINUTE / getBigInterval()){
    //                nextIssueEndTime = DateUtils.skipMin(getBigInterval(),currentIssueEndTime);
    //            }else{
    //                nextIssueEndTime = DateUtils.skipMin(getSmallInterval(),currentIssueEndTime);
    //            }
            }

            nextIssueNumber = nextIssueDay + new DecimalFormat("000").format(nextIssueSub);
            //计算本期
            currentIssueNumber = issueDay + new DecimalFormat("000").format(issueSub);
            if(interval == -1){
                currentIssueEndTime = getBeginDate();
                if(getEndDateTime().isBefore(getBeginDateTime())){
                    previousIssueEndTime=getBeginDate();
                }else{
                    previousIssueEndTime=getEndDateTime().plusDays(-1).toDate();
                }
            }else{
                int skipMin = (DateUtils.getMin() / interval + 1) * interval - DateUtils.getMin();
                currentIssueEndTime = clearSeconds(skipMin(skipMin));
                //指定从上一期减少间隔
                previousIssueEndTime = skipMin(-interval,currentIssueEndTime);
            }
            /**
             * 计算下一期的结束时间
             *
             */
        if(currentIssueEndTime.equals(getEndDate())){
            if(getEndDateTime().isBefore(getBeginDateTime())){
                nextIssueEndTime = getBeginDate();
            }else{
                nextIssueEndTime = getBeginDateTime().plusDays(1).toDate();
            }
        }else{
            /**
             * 当前期数的结束时间与时间转换间隔进行比较
             */
            if(currentIssueEndTime.after(getBeginDate())&&currentIssueEndTime.before(getChangeDate())){
                nextIssueEndTime = skipMin(getBigInterval(),currentIssueEndTime);
            }else{
                nextIssueEndTime = skipMin(getSmallInterval(),currentIssueEndTime);
            }

        }



            row.put("previousIssueNumer",previousIssueNumer);
            row.put("previousIssueEndTime",DateUtils.getDateUnixTimeStamp(previousIssueEndTime));
            row.put("currentIssueNumber",currentIssueNumber);
            row.put("currentIssueEndTime",DateUtils.getDateUnixTimeStamp(currentIssueEndTime));
            row.put("nextIssueNumber",nextIssueNumber);
            row.put("nextIssueEndTime",DateUtils.getDateUnixTimeStamp(nextIssueEndTime));
            return this;
	}

	public Row build() {
		return row;
	}

}
