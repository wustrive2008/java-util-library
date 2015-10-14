package com.wustrive.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class DateTimeUtils {

	private final static Logger log = Logger.getLogger(DateTimeUtils.class);

	/**
	 * 时间格式，如：2009-08-02 13:43
	 */
	public static final String pattern_yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
	/**
	 * 时间格式，如：2009-08-02 13:43
	 */
	public static final String pattern_yyyy_MMdd_HH_mm = "yyyy-MM-ddHH:mm";
	/**
	 * 时间格式，如：2009-08-02 13:43:13
	 */
	public static final String pattern_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 时间格式，如：20090802134313
	 */
	public static final String pattern_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	/**
	 * 时间格式，如：2009-08-02
	 */
	public static final String pattern_yyyy_MM_dd = "yyyy-MM-dd";
	/**
	 * 时间格式，如：2009-08
	 */
	public static final String pattern_yyyy_MM = "yyyy-MM";
	/**
	 * 时间格式，如：20090802
	 */
	public static final String pattern_yyyyMMdd = "yyyyMMdd";
	/**
	 * 时间格式，如：2009-08-02--》090802
	 */
	public static final String pattern_yyMMdd = "yyMMdd";
	/**
	 * 时间格式，如：13:43
	 */
	public static final String pattern_HH_mm = "HH:mm";
	/**
	 * 日期时间格式化字符串
	 */
	public static final String DATE_TIME_FORMAT_STR_1 = "yyyyMM";

	/**
	 * 0点0分0秒
	 */
	public static final String DATE_TIME_HMS_0 = "000000";
	/**
	 * 23点59分59秒
	 */
	public static final String DATE_TIME_HMS_23 = "235959";

	public static Date parseStringToDate(String pattern, String dateString) {
		DateFormat df1 = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = df1.parse(dateString);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 根据规则转换成指定的时间字符串
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String parseDateToString(String pattern, Date date) {
		DateFormat df1 = new SimpleDateFormat(pattern);
		return df1.format(date);
	}

	/**
	 * 将日期字符串有一种格式转为另一种格式
	 * 
	 * @param pattern1
	 *            转换前的格式
	 * @param pattern2
	 *            将要转换的格式
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public static String switchToAnotherString(String pattern1, String pattern2, String dateString) {
		DateFormat df1 = new SimpleDateFormat(pattern1);
		DateFormat df2 = new SimpleDateFormat(pattern2);
		Date date;
		try {
			date = df1.parse(dateString);
		}
		catch (ParseException e) {
			return null;
		}
		return df2.format(date);
	}

	/**
	 * 获取days天后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static Date getEndDateOfTodayByDays(int days) {
		long now = System.currentTimeMillis();

		return new Date(now + days * 24 * 60 * 60 * 1000);
	}

	/**
	 * 更新时间的计算
	 * 
	 * @param publishedDate
	 * @return
	 */
	public static String Datetochange(Date reflshDate) {
		if (reflshDate == null)
			return "";

		Calendar c = Calendar.getInstance();

		c.setTime(reflshDate);

		c.set(	c.get(Calendar.YEAR),
				c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE),
				c.get(Calendar.SECOND));

		Long time = (new Date().getTime()) - (c.getTimeInMillis());

		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = time / dd;
		long hour = (time - day * dd) / hh;
		long minute = (time - day * dd - hour * hh) / mi;

		String strDay = "" + day;
		String strHour = "" + hour;
		String strMinute = "" + minute;
		StringBuffer leavetime = new StringBuffer();
		if (!strDay.equals("0")) {
			leavetime.append(strDay + "天");
		}
		if (!strHour.equals("0")) {
			leavetime.append(strHour + "小时");
		}
		leavetime.append(strMinute + "分钟前");
		return leavetime.toString();
	}

	public static String DateLoginChange(Date loginDate) {
		if (loginDate == null)
			return "";

		Calendar c = Calendar.getInstance();

		c.setTime(loginDate);

		c.set(	c.get(Calendar.YEAR),
				c.get(Calendar.MONTH),
				c.get(Calendar.DAY_OF_MONTH),
				c.get(Calendar.HOUR_OF_DAY),
				c.get(Calendar.MINUTE),
				c.get(Calendar.SECOND));

		Long time = (new Date().getTime()) - (c.getTimeInMillis());

		int ss = 1000;
		int mi = ss * 60;
		int hh = mi * 60;
		int dd = hh * 24;

		long day = time / dd;
		long hour = (time - day * dd) / hh;
		long minute = (time - day * dd - hour * hh) / mi;

		StringBuffer leavetime = new StringBuffer();
		if (day == 0 && hour == 0 && minute < 10) {
			leavetime.append("刚刚来过");
		} else {
			String strDay = "" + day;
			String strHour = "" + hour;
			String strMinute = "" + minute;

			if (!strDay.equals("0")) {
				leavetime.append(strDay + "天");
			}
			if (!strHour.equals("0")) {
				leavetime.append(strHour + "小时");
			}
			leavetime.append(strMinute + "分钟前");
		}
		return leavetime.toString();
	}

	/**
	 * 获取今天的开始时间
	 * 
	 * @return
	 */
	public static Date getBeginDateTimeOfToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取今天的结束时间
	 * 
	 * @return
	 */
	public static Date getEndDateTimeOfToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	@SuppressWarnings("deprecation")
	public static String getYearNo(Date date) {
		int year = date.getYear();
		String yearNo = (year + "").substring(1);
		return yearNo;
	}

	@SuppressWarnings("deprecation")
	public static String getMonthNo(Date date) {
		int month = date.getMonth();
		String monthNo = month + 1 + "";
		if (month < 10) {
			monthNo = "0" + monthNo;
		}
		return monthNo;
	}

	public static String getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return String.valueOf(calendar.get(Calendar.YEAR));
	}

	public static String getMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return String.valueOf(calendar.get(Calendar.MONTH) + 1)
				+ String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static String getFormatMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return String.valueOf(calendar.get(Calendar.MONTH) + 1)
				+ "/"
				+ String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 通过字符串创建一个Date对象
	 * 
	 * @param time
	 *            格式如"2010-06-11"
	 * @return date
	 * @throws ParseException
	 */
	public static Date formatStringToDate(String time) throws ParseException {
		DateFormat sdf = DateFormat.getDateInstance();

		return sdf.parse(time);
	}

	public static String formatDateToString(String pattern, Date date) {

		if (null == date) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(date);
	}

	public static Date formatStringToDate(String pattern, String time) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.parse(time);
	}

	/**
	 * 获取某天的结束时间
	 * 
	 * @return
	 */
	public static Date getEndDateTimeOfDate(Date date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取本周的开始时间
	 * 
	 * @return
	 */
	public static Date getBeginWeekTimeOfToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -(-getMondayPlus()));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据日期获取3天前的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginThreeDays(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据日期获得一季度的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getBeginOfFirstQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 根据所给日期获取当前月的最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndOfMounth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static String getStringCallLong(long callLong) {
		if (0 != callLong) {
			if (callLong < 60) {
				return "00:" + callLong;
			} else if (callLong > 60 && callLong < 3600) {
				return callLong / 60 + ":" + callLong % 60;
			} else {
				return (8000 / 60) / 60 + ":" + (8000 / 60) % 60 + ":" + 8000 % 60;
			}
		}
		return null;
	}

	// 验证一个时间是否为当天以前
	public static boolean isTodayByTime(String date) {
		boolean msg = false;
		Date orderDate = DateTimeUtils.parseStringToDate(	DateTimeUtils.pattern_yyyy_MM_dd_HH_mm_ss,
															date);
		Date endDate = DateTimeUtils.getEndDateTimeOfToday();
		if (orderDate.before(endDate)) {
			msg = true;
		}
		return msg;
	}

	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getTimestamp(Date date) {
		SimpleDateFormat nf = new SimpleDateFormat("yyyyMMddHHmmss");
		String ntime = nf.format(date);
		return ntime;
	}

	// 获得当前日期与本周日相差的天数
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 计算两个日期相差的天数，格式：2008-08-08
	 * 
	 * @param endTime
	 *            结束日期
	 * @param beginTime
	 *            开始日期
	 * @return
	 */
	public static long getQuot(String endTime, String beginTime) {
		long quot = 0;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(endTime);
			Date date2 = ft.parse(beginTime);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000 / 60 / 60 / 24;
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	/**
	 * 根据日期获取日期days天后的日期
	 * 
	 * @param time
	 *            日期:格式yyyy_MM_dd,yyyyMMdd
	 * @param days
	 *            天数
	 * @return
	 * @throws ParseException
	 */
	public static String getEndDateOfTodayByTimeAndDays(String time, int days) {
		try {
			Calendar now = Calendar.getInstance();
			// 判断日期格式
			String yyyy_MM_ddFmt = "[0-9]{4}-[0-9]{2}-[0-9]{2}"; // 格式yyyy_MM_dd
			String yyyyMMddFmt = "[0-9]{4}[0-9]{2}[0-9]{2}"; // 格式yyyyMMdd
			Pattern p = Pattern.compile(yyyy_MM_ddFmt);
			Pattern p1 = Pattern.compile(yyyyMMddFmt);

			if (p.matcher(time).matches()) {
				now.setTime(DateTimeUtils.formatStringToDate(pattern_yyyy_MM_dd, time));
			} else if (p1.matcher(time).matches()) {
				now.setTime(DateTimeUtils.formatStringToDate(pattern_yyyyMMdd, time));
			}
			now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
			return formatDateToString(pattern_yyyyMMdd, now.getTime());

		}
		catch (ParseException e) {
			log.info("根据日期获取日期days天后的日期出错" + e);
			return null;
		}
	}

	/**
	 * 获取当月的最后一天
	 * 
	 * @param dat
	 * @return
	 * @throws ParseException
	 */
	public static String getTheLastDay(String dat) throws ParseException {// yyyy-MM-dd
		String str = "";
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str = "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str = "30";
		} else {
			if (isLeapYear(dat)) {
				str = "29";
			} else {
				str = "28";
			}
		}
		return str;
	}

	/**
	 * 获取指定日期所属月的最后一天
	 * 
	 * @param {String} date
	 * @return {String } date
	 * @throws ParseException
	 */
	public static String getMonthLastDay(String date) throws ParseException {
		String str = "";
		String month = date.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
			str = "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str = "30";
		} else {
			if (isLeapYear(date)) {
				str = "29";
			} else {
				str = "28";
			}
		}
		return date.substring(0, 8) + str;
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 * @throws ParseException
	 */
	public static boolean isLeapYear(String ddate) throws ParseException {
		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = formatStringToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 根据日期计算相差的月数,日期格式yyyy-MM-dd
	 * 
	 * @param beginTime
	 *            开始日期
	 * @param endTime
	 *            结束日期
	 * @return
	 * @throws ParseException
	 */
	public static int monthSpace(String beginTime, String endTime) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		bef.setTime(sdf.parse(beginTime));
		aft.setTime(sdf.parse(endTime));

		int beginYear = bef.get(Calendar.YEAR);// 开始年份
		int endYear = aft.get(Calendar.YEAR);// 结束年份
		int beginM = bef.get(Calendar.MONTH) + 1;// 开始月份
		int endM = aft.get(Calendar.MONTH) + 1;// 结束月份

		// 如果为同一年份
		if (beginYear == endYear) {
			int result = endM - beginM;
			return result + 1;
		} else {
			int i = (12 - beginM + 1) + endM;
			int j = (endYear - beginYear - 1) * 12;
			return i + j;
		}

	}

	/**
	 * 根据日期获取i个月后的日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getEndDateTimeOfMonth(Date date, int i) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, i);
		String time = DateTimeUtils.formatDateToString(pattern_yyyy_MM_dd, calendar.getTime());
		return time;
	}

	/**
	 * 格式化日期为中文
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String getChineseFormatDate(String time) throws ParseException {
		String result = null;
		if (StringUtils.isNotBlank(time)) {
			Date date = formatStringToDate(DateTimeUtils.pattern_yyyy_MM_dd, time);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int month = c.get(Calendar.MONTH);
			String s = DateFormat.getDateInstance(DateFormat.FULL).format(date);
			if (month > 8) {
				result = s.substring(0, 8);
			} else {
				result = s.substring(0, 7);
			}
		}
		return result;
	}

	/**
	 * 小时的加减
	 * 
	 * @param date
	 * @param i
	 * @return
	 */
	public static Date addOneHour(Date date, int i) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, i);
		return calendar.getTime();
	}

	public static Long getCurrentTimeMillis(String pattern, String time) {
		Date date;
		try {
			date = formatStringToDate(pattern, time);
		}
		catch (ParseException e) {
			return null;
		}
		return date.getTime();
	}

	/**
	 * 获取今天的开始时间
	 * 
	 * @return
	 */
	public static String getBeginTimeOfToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取今天的结束时间
	 * 
	 * @return
	 */
	public static String getEndTimeOfToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DATE, 1);
		calendar.add(Calendar.SECOND, -1);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取本周的开始时间
	 * 
	 * @return
	 */
	public static String getBeginTimeOfThisWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -(-getMondayPlus()));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取本周的结束时间
	 * 
	 * @return
	 */
	public static String getEndTimeOfThisWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7 - (-getMondayPlus()));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取当前月的最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getEndTimeOfCurrentMounth() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取当前月的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getBeginTimeOfCurrentMounth() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取当前月的最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getEndTimeOfCurrentMounthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取当前月的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getBeginTimeOfCurrentMounthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取上个月的最后时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getEndTimeOfLastMounthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}

	/**
	 * 获取上个月的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getBeginTimeOfLastMounthByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return formatDateToString(pattern_yyyy_MM_dd_HH_mm_ss, calendar.getTime());
	}
	
	/**
	 * 判断日期是否合法
	 * @param date
	 * @return
	 */
	public static boolean validDate(String date){
		DateFormat df = new SimpleDateFormat(pattern_yyyy_MM_dd);
		df.setLenient(false);
		try{
			df.parse(date);
			return true;
		}catch (Exception e){
        // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * 判断传入的时间是否大于当前时间
	 * @param date
	 * @return
	 */
	public static boolean isLongerThanNow(String date) {
		boolean msg = false;
		Date orderDate = DateTimeUtils.parseStringToDate(	DateTimeUtils.pattern_yyyy_MM_dd_HH_mm_ss,
															date);
		Date nowDate = Calendar.getInstance().getTime();
		if (nowDate.before(orderDate)) {
			msg = true;
		}
		return msg;
	}
	
	/**
	 * 获取两个时间的小时差
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getSubHour(Date date1, Date date2) {
		return (int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 60);
	}
}