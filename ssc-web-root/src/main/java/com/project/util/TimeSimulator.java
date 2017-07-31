package com.project.util;

import java.util.Calendar;
import java.util.Date;

public class TimeSimulator {

	private Calendar calendar = Calendar.getInstance();
	private Calendar beginCalendar = Calendar.getInstance();
	
	public Calendar getBeginCalendar() {
		return beginCalendar;
	}
	public long getTime() {
		return calendar.getTimeInMillis();
	}
	public Date getDate() {
		return calendar.getTime();
	}

	public TimeSimulator(long time, int day, int hour, int minute) {
		calendar.setTimeInMillis(time);
		calendar.add(Calendar.DATE, day);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.MINUTE, minute);
		beginCalendar.setTimeInMillis(getTime());
	}

	public TimeSimulator(long time) {
		this(time, 0, 0, 0);
	}

	public TimeSimulator() {
		this(System.currentTimeMillis(), 0, 0, 0);
	}

	public TimeSimulator(int day, int hour, int minute) {
		this(System.currentTimeMillis(), day, hour, minute);
	}

	public TimeSimulator setDayBegin() {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		beginCalendar.setTimeInMillis(getTime());
		return this;
	}

	public TimeSimulator getFiveMinuteAfter() {
		calendar.add(Calendar.MINUTE, 5);
		return this;
	}
	
	public Date getMinuteAfterWithoutModify(int minute) {
		Calendar calendarTemp = Calendar.getInstance();
		calendarTemp.setTime(calendar.getTime());
		calendarTemp.add(Calendar.MINUTE, minute);
		return calendarTemp.getTime();
	}

	public static int getDayNum(Calendar calendar) {

		return calendar.get(Calendar.YEAR) * 365 + calendar.get(Calendar.DAY_OF_YEAR);
	}
	//avoid 0 点
	public boolean isNextDay() {
		return (getDayNum(calendar) - getDayNum(beginCalendar)) >= 1 && calendar.get(Calendar.MINUTE) > 0;
	}
	
	public int getTimeSliceKey(){
		return (calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE))/5;
	}
	
	public static int getTimeSliceKey(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return (calendar.get(Calendar.HOUR_OF_DAY)*60 + calendar.get(Calendar.MINUTE))/5;
	}
	
	public boolean inStopGrapSegment(){
		return calendar.get(Calendar.HOUR_OF_DAY)==3||calendar.get(Calendar.HOUR_OF_DAY)==4||calendar.get(Calendar.HOUR_OF_DAY)==5;
	}
}
