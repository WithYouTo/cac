package com.qcap.core.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 阿里云短信服务工具类
 * 
 * @author 彭浩
 * @date 2018/6/6 17:30
 * @version 1.0
 */
public class SmsUtil {

	/**
	 * 调用阿里云的短信服务接口
	 * 
	 * @date 2018/6/6 17:26
	 * @param url
	 *            阿里短信服务url
	 * @return java.util.Map<java.lang.String> , java.lang.String>
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> sms(String url) throws Exception {
		String getRequest = HttpUtil.doGet(url);
		if (getRequest != null && !getRequest.isEmpty()) {
			return JSONObject.parseObject(getRequest, Map.class);
		}
		return null;
	}

}
