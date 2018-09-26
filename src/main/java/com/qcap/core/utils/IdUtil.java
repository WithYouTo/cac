package com.qcap.core.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IdUtil {

	//sk01
	public static String getID() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000 - 10000) + 10000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SF" + date + number;
		return str;
	}

	public static String getWeixin() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (1000000 - 100000) + 100000);
			break;
		}
		String number = String.valueOf(num);
		String str = "YJF" + date + number;//预缴费
		return str;
	}

	public static int getNumber() {
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (1000000 - 100000) + 100000);
			break;
		}
		return num;
	}

	/**
	 * 生成物业费订单号
	 * @Title: getWyf 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getPropertyOrder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SQWY" + date + number;
		return str;
	}

	/**
	 * 生成房产租金订单号
	 * @Title: getFcRentOrder 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getFcRentOrder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SQFC" + date + number;
		return str;
	}

	/**
	 * 生成房改房租金订单号
	 * @Title: getFgfRentOrder 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getFgfRentOrder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SQFGF" + date + number;
		return str;
	}

	/**
	 * 生成水费订单号
	 * @Title: getWyf 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getWaterOrder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SQSW" + date + number;
		return str;
	}

	/**
	 * 生成预存款订单号
	 * @Title: getWyf 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getDepositOrder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SQPY" + date + number;
		return str;
	}

	//月卡车缴停车费
	public static String getCarFee() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SQCF" + date + number;
		return str;
	}

	//临停车缴停车费
	public static String getFace2Pay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SQMS" + date + number;
		return str;
	}

	//生成面收缴费订单号
	public static String getTempCar() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = "SQTC" + date + number;
		return str;
	}

	//取得每月的最后一天
	public static String getLastDay(String debtDate) {
		//获取年
		String year = debtDate.substring(0, 4);
		//获取年
		String month = debtDate.substring(4, 6);
		//初始化日期类
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		//定义输入格式
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		//获取欠费月份的最后一天
		String lastDate = formatter.format(cal.getTime());
		return lastDate;
	}

	//取得unix时间戳
	public static String getTimeStamp(String str) {
		Calendar oldDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = null;
		String timestamp = null;
		try {
			d = sdf.parse(str);
			oldDate.setTime(d);
			long timeOld = oldDate.getTimeInMillis();
			int time = (int) (timeOld / 1000);
			timestamp = String.valueOf(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timestamp;
	}

	/**
	 * 获取用户端实际ip
	 * @Title: test 
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: String
	 */
	public static String getUserIp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
																// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
	
	

	/**
	 * 根据数据库前缀生成相应订单号
	 * @Title: getOutorderNo 
	 * @Description: TODO
	 * @param orderPrefix
	 * @return
	 * @return: String
	 */
	public static String getOutTradeNo(String orderPrefix) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		String formattedDate = sdf.format(new Date());
		String date = formattedDate.toString();
		Random random = new Random();
		int num = -1;
		while (true) {
			num = (int) (random.nextDouble() * (100000000 - 10000000) + 10000000);
			break;
		}
		String number = String.valueOf(num);
		String str = orderPrefix + date + number;
		return str;
	}
}
