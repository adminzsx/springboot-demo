package com.manage.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtil {

	private final static Logger logger = LoggerFactory.getLogger(TimeUtil.class);

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static String format_pattern_default = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy-MM-dd
	 */
	public final static String format_pattern_short = "yyyy-MM-dd";
	
	/**
     * 把long 转换成 日期  yyyy-MM-dd HH:mm:ss
     */
    public static Date transferLongToDate(Long millSec) {
        Date date = new Date(millSec);
        return date;
    }

	/**
	 * 时间间隔 秒
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDateTimeDif(Date startDate, Date endDate) {
		try {
			// 毫秒ms
			long diff = endDate.getTime() - startDate.getTime();
			return diff / 1000;
		} catch (Exception e) {
			logger.error("getDateTimeDif error");
		}
		return 0;
	}
	
	/**
	 * 时间间隔  天
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDateTimeDifDay(Date startDate, Date endDate) {
		try {
		 
			long diffsecond = getDateTimeDif(startDate,endDate);
			return diffsecond /60/60/24;
		} catch (Exception e) {
			logger.error("getDateTimeDifDay error");
		}
		return 0;
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static Date parseString(String dateStr, String pattern) {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}
		DateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
	
	 /**
     *  获取当天的小时数
     * @param date
     * @return
     */
    public static int getHourOfDayByDate(Date date){
        if(date==null){
            return -1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

	/**
	 * 获取月当中的周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 获取月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearFromDate(Date date) {
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 *
	 * 获取当前日期相加秒数的日期
	 */
	public static Date addSecondOfCurrentDate(Date date, int seconds) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	/**
	 *
	 * 获取当前日期相加分钟数的日期
	 */
	public static Date addMinutesOfCurrentDate(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}
	
	/**
	 *
	 * 获取当前日期相加天数的日期
	 */
	public static Date addDaysOfCurrentDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	/**
	 *
	 * 获取当前日期相加年数的日期
	 */
	public static Date addMonthsOfCurrentDate(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}
	
	/**
	 *
	 * 获取当前日期相加年数的日期
	 */
	public static Date addYearsOfCurrentDate(Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
	
 
 
}
