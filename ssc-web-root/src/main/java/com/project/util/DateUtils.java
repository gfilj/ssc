package com.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	/**
	 * Date time formatter
	 */
	public static final String DATEMONTH_FM = "yyyy-MM", DATE_FM = "yyyy-MM-dd", DATETIME_FM = "yyyy-MM-dd HH:mm:ss",
			TIME_FM = "HH:mm:ss", DATE_MIGU = "yyyyMMddHH", DATETIME_DETAIL = "yyyyMMddHHmmss";

	/**
	 * 取得当前时间戳（精确到秒）unix
	 *
	 * @return nowTimeStamp
	 */
	public static long getUnixNowTimeStamp() {
		long time = System.currentTimeMillis();
		return time / 1000;
	}
	
	/**
	 * 获取当前时间增加后的unix时间戳
	 */
	public static long getUnixNowTimeStamp(int calendarType, int skipLen) {
		long time = skip(calendarType, skipLen).getTime();
		return time / 1000;
	}
	/**
	 * 获取日期对象
	 * 
	 * @param d
	 * @return
	 */
	private static Date getDate(Date... d) {
		return d != null && d.length > 0 ? d[0] : new Date();
	}

	/**
	 * 格式化参数Date,格式:yyyy-MM-dd HH:mm:ss
	 * 
	 * @param d
	 *            为空或者不传递则为当前时间
	 * @return
	 */
	public static String formatDateTime(Date... d) {
		return format(DATETIME_FM, getDate(d));
	}

	public static String formatDateDetail(Date... d) {
		return format(DATETIME_DETAIL, getDate(d));
	}

	/**
	 * 格式化参数Date,格式:yyyy-MM-dd
	 * 
	 * @param d
	 *            为空或者不传递则为当前时间
	 * @return
	 */
	public static String formatDate(Date... d) {
		return format(DATE_FM, getDate(d));
	}

	/**
	 * 格式化参数Date,格式:yyyy-MM
	 * 
	 * @param d
	 *            为空或者不传递则为当前时间
	 * @return
	 */
	public static String formatMonth(Date... d) {
		return format(DATE_FM, getDate(d));
	}

	// 特殊处理广告：15477
	public static String formatMigu(Date... d) {
		return format(DATE_MIGU, getDate(d));
	}

	/**
	 * 格式化参数时间
	 * 
	 * @param pattern
	 *            - ""
	 * @param d
	 *            - Date
	 * @return the formatted date-time string
	 * @see SimpleDateFormat
	 */
	public static String format(String pattern, Date... d) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(getDate(d));
	}

	/**
	 * 转换日期字符为Date对象
	 * 
	 * @param pattern
	 *            - the pattern of the string
	 * @param strDateTime
	 *            - the string to be parsed
	 * @return A Date parsed from the string. In case of error, returns null.
	 */
	public static Date parse(String pattern, String strDateTime) {
		Date date = null;
		if (strDateTime == null || pattern == null)
			return null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			formatter.setLenient(false);
			date = formatter.parse(strDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 转换日期字符为Date对象，格式为
	 * 
	 * @param strDateTime
	 *            - the string to be parsed
	 */
	public static Date parseDateTime(String strDateTime) {
		if (strDateTime == null)
			return null;
		return parse(DATETIME_FM, strDateTime);
	}

	/**
	 * 指定在当前时间下增加秒/分/时/天/月/年
	 * 
	 * @param calendarType(Calendar.类型)
	 * @param skipLen
	 *            : 累加长度(可负)
	 * @return
	 */
	public static Date skip(int calendarType, int skipLen) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendarType, skipLen);
		return calendar.getTime();
	}

	/**
	 * 指定在当前时间下增加
	 * 
	 * @param calendarType(Calendar.类型)
	 * @param skipLen
	 *            : 累加长度(可负)
	 * @param d
	 *            为空或者不传递则为当前时间
	 * @return
	 */
	public static Date skip(int calendarType, int skipLen, Date... d) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(d));
		calendar.add(calendarType, skipLen);
		return calendar.getTime();
	}

	/**
	 * 跳动月
	 * 
	 * @param skipLen
	 *            可为负数
	 * @param d
	 *            为空或者不传递则为当前时间
	 * @return
	 */
	public static Date skipMonth(int skipLen, Date... d) {
		return skip(Calendar.MONTH, skipLen, d);
	}

	/**
	 * 跳动日期
	 * 
	 * @param skipLen
	 *            可为负数
	 * @param d
	 *            为空或者不传递则为当前时间
	 * @return
	 */
	public static Date skipDay(int skipLen, Date... d) {
		return skip(Calendar.DATE, skipLen, d);
	}

	/**
	 * 跳动小时
	 * 
	 * @param skipLen
	 *            可为负数
	 * @param d
	 *            为空或者不传递则为当前时间
	 * @return
	 */
	public static Date skipHour(int skipLen, Date... d) {
		return skip(Calendar.HOUR, skipLen, d);
	}

	/**
	 * 跳动秒
	 * 
	 * @param skipLen
	 *            可为负数
	 * @param d
	 *            为空或者不传递则为当前时间
	 * @return
	 */
	public static Date skipSecond(int skipLen, Date... d) {
		return skip(Calendar.SECOND, skipLen, d);
	}

	/**
	 * 日期参数第一个是否在第二个日期之前
	 * 
	 * @param date1
	 * @param date2:如不填写或者为null，则判断第一个日期是否在当前时间之前
	 * @return
	 */
	public static boolean before(Date date1, Date... date2) {
		return date1.before(getDate(date2));
	}

	/**
	 * 日期参数第一个是否在第二个日期之前
	 * 
	 * @param date1
	 * @param date2:如不填写或者为null，则判断第一个日期是否在当前时间之前
	 * @return
	 */
	public static boolean before(Object date1, Date... date2) {
		return ((Date) date1).before(getDate(date2));
	}

	/**
	 * 参数日期字符比当前日期（精确到天）是否早
	 * 
	 * @param strDateTime
	 * @return
	 */
	public static boolean beforeDay(String strDateTime) {
		Date date = parse(DATETIME_FM, strDateTime);
		return date.before(new Date());
	}

	/**
	 * 判断年份是否为闰年
	 * 
	 * @param year
	 *            年份
	 */
	public static boolean isLeapYear(int year) {
		if ((((year % 4) == 0) && ((year % 100) != 0)) || ((year % 4) == 0) && ((year % 400) == 0)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取今年
	 */
	public static int getYear() {
		return get(Calendar.YEAR);
	}

	/**
	 * 获取今月
	 */
	public static int getMonth() {
		return get(Calendar.MONTH);
	}

	/**
	 * 获取今日
	 */
	public static int getDay() {
		return get(Calendar.DAY_OF_MONTH);
	}

	public static int getHour() {
		return get(Calendar.HOUR_OF_DAY);
	}

	public static int getMin() {
		return get(Calendar.MINUTE);
	}

	public static int getSec() {
		return get(Calendar.SECOND);
	}

	public static int get(int calendarType) {
		Calendar c = Calendar.getInstance();
		return c.get(calendarType);
	}

	public static int getYYYYMMDD(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int created = year * 10000 + mon * 100 + day;
		return created;
	}

	public static int getYYYYMM(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH) + 1;
		int created = year * 100 + mon;
		return created;
	}

	/**
	 * 获取当前年月日
	 * 
	 * @return
	 */
	public static int getYYYYMMDD() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int created = year * 10000 + mon * 100 + day;
		return created;
	}

	/**
	 * 返回year年份month月份的天数
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public static int getMonthDays(int month, int year) {
		if ((isLeapYear(year) == true) && (month == 2)) {
			return 29;
		} else if ((isLeapYear(year) == false) && (month == 2)) {
			return 28;
		}
		if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10)
				|| (month == 12)) {
			return 31;
		}
		return 30;
	}

	public static String getBeforeDate(int num) {
		String format = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -num);

		String date = sdf.format(calendar.getTime());
		return date;
	}

}
