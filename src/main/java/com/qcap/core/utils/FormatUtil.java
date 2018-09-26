package com.qcap.core.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormatUtil {
	private static Object Server;
	/**
	 * 用于过滤数值型的字母和符号
	 */
	private static final Pattern numFilter = Pattern.compile("[^0-9.+-]");

	/**
	 * 把对象转换成字符串
	 * 
	 * @param obj
	 * @return String 转换成字符串,若对象为null,则返回空字符串.
	 */
	public static String toString(Object obj) {
		if (obj == null)
			return "";

		return obj.toString();
	}

	/**
	 * 把对象转换为int数值.
	 * 
	 * @param obj
	 *            包含数字的对象.
	 * @return int 转换后的数值,对不能转换的对象返回0。
	 */
	public static int toInt(Object obj) {
		int a = 0;
		try {
			if (obj != null)
				a = Integer.parseInt(obj.toString());
		} catch (Exception e) {

		}
		return a;
	}

	/**
	 * 将obj转换成BigDecimal;如果obj==null，则返回0
	 * 
	 * @param obj
	 * @return
	 */
	public static final BigDecimal toBc(Object obj) {
		BigDecimal bc = BigDecimal.ZERO;
		if (null != obj) {
			if (obj instanceof BigDecimal) {
				bc = (BigDecimal) obj;
			} else if (obj instanceof String) {
				String value = numFilter.matcher((String) obj).replaceAll("");
				if (!"".equals(value)) {
					bc = new BigDecimal(value);
				}
			} else if (obj instanceof Integer) {
				bc = new BigDecimal(((Integer) obj).intValue());
			} else if (obj instanceof Long) {
				bc = new BigDecimal(((Long) obj).longValue());
			} else if (obj instanceof Number) {
				bc = new BigDecimal(((Number) obj).doubleValue());
			}
		}
		return bc;
	}

	/**
	 * 将obj转换成BigDecimal ;如果obj==null，返回0
	 * 
	 * @param obj
	 * @param scale精度
	 * @return
	 */
	public static BigDecimal toBc(Object obj, int scale) {
		BigDecimal bc = new BigDecimal("0").setScale(scale, BigDecimal.ROUND_HALF_UP);
		if (scale < 0) {
			scale = 0;
		}
		if (null != obj) {
			if (obj instanceof BigDecimal) {
				bc = (BigDecimal) obj;
			} else if (obj instanceof String) {
				String value = numFilter.matcher((String) obj).replaceAll("");
				if (!"".equals(value)) {
					bc = new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP);
				}
			} else if (obj instanceof Integer) {
				bc = new BigDecimal(((Integer) obj).intValue()).setScale(scale, BigDecimal.ROUND_HALF_UP);
			} else if (obj instanceof Long) {
				bc = new BigDecimal(((Long) obj).longValue()).setScale(scale, BigDecimal.ROUND_HALF_UP);
			} else if (obj instanceof Number) {
				bc = new BigDecimal(((Number) obj).doubleValue()).setScale(scale, BigDecimal.ROUND_HALF_UP);
			}
		}
		return bc;
	}

	/**
	 * 将obj转换成char;如果obj==null，则返回‘’
	 * 
	 * @param obj
	 * @return
	 */
	public static Character toChar(Object object) {
		if (object == null || object.toString().trim().length() == 0) {
			return (char) Character.UNASSIGNED;
		} else {
			return object.toString().trim().charAt(0);
		}
	}

	/**
	 * 将obj转换成long;如果obj==null，则返回0L
	 * 
	 * @param obj
	 * @return
	 */
	public static Long toLong(Object obj) {
		Long ret = 0L;
		if (null != obj) {
			if (obj instanceof BigDecimal) {
				ret = ((BigDecimal) obj).longValue();
			} else if (obj instanceof String) {
				String value = numFilter.matcher((String) obj).replaceAll("");
				if (!"".equals(value)) {
					ret = new BigDecimal(value).longValue();
				}
			} else if (obj instanceof Long) {
				ret = ((Long) obj).longValue();
			} else if (obj instanceof Integer) {
				ret = ((Integer) obj).longValue();
			} else if (obj instanceof Number) {
				ret = ((Number) obj).longValue();
			}
		}

		return ret;
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * 获取当前日期 yyyyMMdd
	 * 
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	// 生成随机数字和字母
	public static String getStringRandom(int length) {
		try {
			String val = "";
			Random random = new Random();

			// 参数length，表示生成几位随机数
			for (int i = 0; i < length; i++) {

				String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
				// 输出字母还是数字
				if ("char".equalsIgnoreCase(charOrNum)) {
					// 输出是大写字母还是小写字母
					int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
					val += (char) (random.nextInt(26) + temp);
				} else if ("num".equalsIgnoreCase(charOrNum)) {
					val += String.valueOf(random.nextInt(10));
				}
			}
			return val;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取编码字符集
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */

	public static String getCharacterEncoding(HttpServletRequest request, HttpServletResponse response) {

		if (null == request || null == response) {
			return "gbk";
		}

		String enc = request.getCharacterEncoding();
		if (null == enc || "".equals(enc)) {
			enc = response.getCharacterEncoding();
		}

		if (null == enc || "".equals(enc)) {
			enc = "gbk";
		}

		return enc;
	}

	public static String URLencode(String content) {

		String URLencode;

		URLencode = replace(Server.equals(content), "+", "%20");

		return URLencode;
	}

	private static String replace(boolean equals, String string, String string2) {

		return null;
	}

	/**
	 * 获取unix时间，从1970-01-01 00:00:00开始的秒数
	 * 
	 * @param date
	 * @return long
	 */
	public static long getUnixTime(Date date) {
		if (null == date) {
			return 0;
		}

		return date.getTime() / 1000;
	}

	public static String QRfromGoogle(String chl) {
		int widhtHeight = 300;
		String EC_level = "L";
		int margin = 0;
		String QRfromGoogle;
		chl = URLencode(chl);

		QRfromGoogle = "http://chart.apis.google.com/chart?chs=" + widhtHeight + "x" + widhtHeight + "&cht=qr&chld="
				+ EC_level + "|" + margin + "&chl=" + chl;

		return QRfromGoogle;
	}

	/**
	 * 时间转换成字符串
	 * 
	 * @param date
	 *            时间
	 * @param formatType
	 *            格式化类型
	 * @return String
	 */
	public static String date2String(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date);
	}

	/*
	 * 字符串转时间
	 */
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

	/*
	 * 字符串转时间
	 */
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

	/*
	 * 时间戳转date
	 */
	public static Date longToDateTime(long timeStamp) {
		Date date = null;
		try {
			System.out.println(timeStamp);
			// String转Date
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String d = format.format(timeStamp);
			System.out.println(d);
			date = format.parse(d);
			System.out.println(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
