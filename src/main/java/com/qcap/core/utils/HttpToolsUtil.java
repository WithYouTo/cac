package com.qcap.core.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class HttpToolsUtil {

	/**
	 * 获取request将ParamMap参数转换为MAP
	 * 
	 * @Title: getParamMap
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: Map<String,String>
	 */
	public static Map<String, String> getParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<String, String>();
		Map<String, String[]> requestMap = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = requestMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			if (entry.getValue().length == 1) {
				paramMap.put(entry.getKey(), entry.getValue()[0]);
			} else {
				String[] values = entry.getValue();
				String value = "";
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
				paramMap.put(entry.getKey(), value);
			}
		}
		return paramMap;
	}

	public static Map<String, Map<String, String>> getAlipayParamMap(Map<String, String> map) {
		Map<String, Map<String, String>> paramMap = new HashMap<String, Map<String, String>>();
		Map<String, String> IBMap = new HashMap<String, String>();
		Map<String, String> alipayMap = new HashMap<String, String>();
		for (String key : map.keySet()) {
			String value = map.get(key);
			if (key.indexOf("ib_") > -1) {
				IBMap.put(key.replace("ib_", ""), value);
			} else if (key.indexOf("bc_") > -1) {
				alipayMap.put(key, value);
			}
		}
		paramMap.put("IBMap", IBMap);
		paramMap.put("alipayMap", alipayMap);
		return paramMap;
	}

}