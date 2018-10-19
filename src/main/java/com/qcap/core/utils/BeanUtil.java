package com.qcap.core.utils;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 阿里云短信服务工具类
 * 
 * @author 彭浩
 * @date 2018/6/6 17:30
 * @version 1.0
 */
public class BeanUtil {

	@SuppressWarnings("unchecked")
	public static Map<String, String> bean2Map(Object object) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		String value = mapper.writeValueAsString(object);
		return JSONObject.parseObject(value, Map.class);
	}

}
