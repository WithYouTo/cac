package com.qcap.core.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

public class DateChangeUtil {

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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static String convertWorktime(String time) {
		Integer IntNo = time.indexOf('.');
		if (IntNo == -1) {
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
		DateTime dateTime = new DateTime();
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static String getStringNum(int n) throws Exception {
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

	public static String getStringDateStr(String dateStr) throws Exception {
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
