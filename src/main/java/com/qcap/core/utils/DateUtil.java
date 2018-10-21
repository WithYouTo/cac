/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qcap.core.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

/**
 * 日期时间工具类
 * 
 * @package: com.whxx.core.utils
 * @ClassName: DateUtil.java
 * @author: 彭浩
 * @date: 2018/6/19 10:51
 * @version: 1.0
 */
public class DateUtil {

	/**
	 * 获取YYYY格式
	 *
	 * @return
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 获取YYYY格式
	 *
	 * @return
	 */
	public static String getYear(Date date) {
		return formatDate(date, "yyyy");
	}

	/**
	 * 获取YYYY-MM-DD格式
	 *
	 * @return
	 */
	public static String getDay() {
		return formatDate(new Date(), "yyyy-MM-dd");
	}

	/**
	 * 获取YYYY-MM-DD格式
	 *
	 * @return
	 */
	public static String getDay(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays() {
		return formatDate(new Date(), "yyyyMMdd");
	}

	/**
	 * 获取YYYYMMDD格式
	 *
	 * @return
	 */
	public static String getDays(Date date) {
		return formatDate(date, "yyyyMMdd");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss.SSS格式
	 *
	 * @return
	 */
	public static String getMsTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
	}

	/**
	 * 获取YYYYMMDDHHmmss格式
	 *
	 * @return
	 */
	public static String getAllTime() {
		return formatDate(new Date(), "yyyyMMddHHmmss");
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 *
	 * @return
	 */
	public static String getTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String pattern) {
		String formatDate = null;
		if (StringUtils.isNotBlank(pattern)) {
			formatDate = DateFormatUtils.format(date, pattern);
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * @Title: compareDate
	 * @Description:(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 *             luguosui
	 */
	public static boolean compareDate(String s, String e) {
		if (parseDate(s) == null || parseDate(e) == null) {
			return false;
		}
		return parseDate(s).getTime() >= parseDate(e).getTime();
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static Date parseDate(String date) {
		return parse(date, "yyyy-MM-dd");
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static Date parseTime(String date) {
		return parse(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		try {
			return DateUtils.parseDate(date, pattern);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期
	 *
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	/**
	 * 把日期转换为Timestamp
	 *
	 * @param date
	 * @return
	 */
	public static Timestamp format(Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 校验日期是否合法
	 *
	 * @return
	 */
	public static boolean isValidDate(String s) {
		return parse(s, "yyyy-MM-dd HH:mm:ss") != null;
	}

	/**
	 * 校验日期是否合法
	 *
	 * @return
	 */
	public static boolean isValidDate(String s, String pattern) {
		return parse(s, pattern) != null;
	}

	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 *
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 *
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 *
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);

		return dateStr;
	}

	public static String dateTimeToString(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = sdf.format(date);
		return formattedDate;

	}

	// 时间转成字符串编号
	public static String dateTimeToStringForId(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String formattedDate = sdf.format(date);
		return formattedDate;

	}

	// 时间转成字符串编号LINENO
	public static String dateTimeToStringForLineNo(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String formattedDate = sdf.format(date);
		return formattedDate;

	}

	public static Date stringToDateTime(String str) {
		Date date = null;
		try {
			// String转Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return date;
	}

	public static Date getAnyFormatDate(String time, String format) {
		Date date = null;
		try {
			// String转Date
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(time);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return date;
	}

	public static String dateTimeToStringForImage(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String formattedDate = sdf.format(date);
		return formattedDate;

	}

	public static String dateTimeToStringForLastDay(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(date) + " 23:59:59";
		return formattedDate;

	}

	public static String dateToString(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(date);
		return formattedDate;

	}

	public static String dateToMonth(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String formattedDate = sdf.format(date);
		return formattedDate;

	}

	public static String dateToDay(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String formattedDate = sdf.format(date);
		return formattedDate;

	}

	public static Date stringToDate(String str) {
		Date date = null;
		try {
			// String转Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return date;
	}

	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}

		return weekDays[w];
	}

	public static String convertWorktime(String time) {
		Integer intNo = time.indexOf('.');
		if (intNo == -1) {
			if (time.length() == 1) {
				time = "0" + time + ":" + "00";
				return time;
			}
			if (time.length() == 2) {
				time = time + ":" + "00";
				return time;
			}
			return time;
		} else {
			if (time.length() == 3) {
				String tempStr1 = time.substring(0, 1);
				String tempStr2 = time.substring(1);
				tempStr2 = "0" + tempStr2;
				BigDecimal num1 = new BigDecimal(tempStr2);
				BigDecimal num2 = new BigDecimal(60);
				num1 = num1.multiply(num2);
				tempStr2 = num1.toString().substring(0, 2);
				tempStr1 = "0" + tempStr1 + ":" + tempStr2;
				return tempStr1;
			}
			if (time.length() == 4) {
				String tempStr1 = time.substring(0, 2);
				String tempStr2 = time.substring(2);
				tempStr2 = "0" + tempStr2;
				BigDecimal num1 = new BigDecimal(tempStr2);
				BigDecimal num2 = new BigDecimal(60);
				num1 = num1.multiply(num2);
				tempStr2 = num1.toString().substring(0, 2);
				tempStr1 = tempStr1 + ":" + tempStr2;
				return tempStr1;
			}
		}
		return time;
	}

	public static Date nextDayTime(int i) {
		Date nextDateTime = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
					0, 0, 0);
			calendar.add(Calendar.HOUR_OF_DAY, i);
			nextDateTime = calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nextDateTime;
	}

	/**
	 * 获取当月的第一天和最后一天
	 *
	 * @param date
	 *            Date型日期
	 * @param format
	 *            yyyyMMdd格式
	 * @return 返回数组
	 */
	public static final String[] getStartEndDayInMonth(Date date, String format) {
		String[] arr = new String[2];
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String curDate = sdf.format(date);
		arr[0] = curDate.substring(0, curDate.length() - 2) + "01";
		org.joda.time.DateTime dateTime = new DateTime();
		arr[1] = curDate.substring(0, curDate.length() - 2)
				+ String.valueOf(dateTime.dayOfMonth().withMaximumValue().dayOfMonth().get());
		return arr;
	}

	/**
	 * 返回当月第一天的日期
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		Date lastDate = calendar.getTime();
		return lastDate;
	}

	/**
	 * 返回当月最后一天的日期
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date lastDate = calendar.getTime();
		return lastDate;
	}

	// excel时间转化2017.08.10----》date
	public static Date stringConvertDate1(String str) {
		Date date = null;
		try {
			// String转Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
			date = sdf.parse(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return date;
	}

	// excel时间转化如2017/08/10----》date
	public static Date stringConvertDate2(String str) {
		Date date = null;
		try {
			// String转Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			date = sdf.parse(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return date;
	}

	// excel时间转化如2017-08-10----》date
	public static Date stringConvertDate3(String str) {
		Date date = null;
		try {
			// String转Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return date;
	}

	public static String getStringNum(int n) {
		String result = "";
		if (n >= 0 && n < 10) {
			result = "00" + n;
		} else if (n >= 10 && n < 100) {
			result = "0" + n;
		} else {
			result = String.valueOf(n);
		}
		return result;
	}

	public static String getStringDateStr(String dateStr) {
		String result = "";
		if (null == dateStr || "".equals(dateStr)) {
			return result;
		}
		dateStr = dateStr.substring(0, 8);
		result = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6, 8);
		return result;
	}

	public static Date string2Date(String str) {
		Date date = null;
		try {
			// String转Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			date = sdf.parse(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return date;
	}

	public static Date changeStringToDate(String str) {
		Date date = null;
		try {
			// String转Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(str);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取给定时间指定偏移量的新时间
	 * 
	 * @date: 2018/6/19 10:48
	 * @param: [old,
	 *             field, amount]
	 * @return: java.util.Date
	 * @version: 1.0
	 */
	public static Date getShiftDate(Date old, int field, int amount) {
		Date date = null;
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(old);
			calendar.add(field, amount);
			date = calendar.getTime();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

}
