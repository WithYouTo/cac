package com.qcap.core.utils;

import java.util.HashMap;
import java.util.Map;

public class CommUtil {

	/**
	 * 获取class路径，末尾含"/"
	 */
	public final static String getClassPath() {
		String path = CommUtil.class.getClassLoader().getResource("").getPath();
		return path;
	}

	/**
	 * 组装返回包
	 * 
	 * @param code
	 *            返回码
	 * @param desc
	 *            描述
	 * @param data
	 *            数据
	 */
	public final static Map<String, Object> setMessage(Object code, String desc, Object data) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("desc", desc);
		map.put("data", data);
		return map;
	}

	/**
	 * 组装返回包
	 * 
	 * @param code
	 *            返回码
	 */
	public final static Map<String, Object> setMessage(String code) {
		return setMessage(code, "", "");
	}
}

