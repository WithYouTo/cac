package com.qcap.core.utils;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 常量类
 */
public class CommConst {
	public final static String success = "success";
	public final static String failed = "failed";

	public final static String SUCCESS = "SUCCESS";
	public final static String FAIL = "FAIL";
	public final static String OK = "OK";

	public final static String utf8 = "UTF-8";
	public final static String gbk = "GBK";
	public final static String gb18030 = "GB18030";

	public final static String yMd1 = "yyyyMMdd";
	public final static String yMd2 = "yyyy/MM/dd";
	public final static String yMd3 = "yyyy-MM-dd";
	public final static String yMd4 = "yyyy年MM月dd日";
	public final static String yMdHms1 = "yyyy-MM-dd HH:mm:ss";
	public final static String yMdHmsS1 = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * yyyyMMdd
	 */
	public final static DateTimeFormatter dtf_yMd1 = DateTimeFormat.forPattern(yMd1);

	/**
	 * yyyyMMdd
	 */
	public final static DateTimeFormatter dtf_yMd3 = DateTimeFormat.forPattern(yMd3);

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static DateTimeFormatter dtf_yMdHms1 = DateTimeFormat.forPattern(yMdHms1);
	/**
	 * yyyy-MM-dd HH:mm:ss.SSS
	 */
	public final static DateTimeFormatter dtf_yMdHmsS1 = DateTimeFormat.forPattern(yMdHmsS1);
}