package com.qcap.cac.tools;

import java.text.SimpleDateFormat;

public class DateTool {

	/**
	 * 
	 * @Title: validDateFormat   
	 * @Description: 验证日期字符串是否为指定格式   
	 * @param: @param dateStr
	 * @param: @param format
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean validDateFormat(String dateStr, String format) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			sdf.parse(dateStr);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @Title: dateFormat   
	 * @Description: 日期字符串格式互相转换    
	 * @param: @param sFormat
	 * @param: @param tFormat
	 * @param: @param dateStr
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: String      
	 * @throws
	 */
	public static String dateFormat(String sFormat, String tFormat, String dateStr) throws Exception {
		SimpleDateFormat sSdf = new SimpleDateFormat(sFormat);
		SimpleDateFormat tSdf = new SimpleDateFormat(tFormat);

		return tSdf.format(sSdf.parse(dateStr));
	}
}
