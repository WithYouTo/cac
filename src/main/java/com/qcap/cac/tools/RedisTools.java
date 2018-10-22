package com.qcap.cac.tools;

import com.alibaba.fastjson.JSONObject;
import com.qcap.core.common.SettingProperties;
import com.qcap.core.entity.TbManager;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.SpringContextHolder;

import cn.hutool.core.util.StrUtil;
import com.qcap.core.utils.jwt.JwtProperties;
import com.qcap.core.utils.jwt.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;

public class RedisTools {

	public static void setCommonConfig(String key, String value) {
		RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
		key = AppUtils.getApplicationName() + StrUtil.COLON + SettingProperties.serviceType + StrUtil.COLON + key;
		redisUtil.setNotExpired(key, value);
	}

	public static String getCommonConfig(String key) {
		RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
		key = AppUtils.getApplicationName() + StrUtil.COLON + SettingProperties.serviceType + StrUtil.COLON + key;
		return redisUtil.get(key);
	}

	public static void setCommonConfig(String type, String key, String value) {
		RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
		key = AppUtils.getApplicationName() + StrUtil.COLON + type + StrUtil.COLON + key;
		redisUtil.setNotExpired(key, value);
	}

	public static String getCommonConfig(String type, String key) {
		RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
		key = AppUtils.getApplicationName() + StrUtil.COLON + type + StrUtil.COLON + key;
		return redisUtil.get(key);
	}

	public static String getUserName(HttpServletRequest request) {
		RedisUtil redisUtil = SpringContextHolder.getBean(RedisUtil.class);
		JwtProperties jwtProperties = SpringContextHolder.getBean(JwtProperties.class);
		JwtTokenUtil jwtTokenUtil = SpringContextHolder.getBean(JwtTokenUtil.class);

		String token = request.getHeader(jwtProperties.getTokenHeader());
		String userId = jwtTokenUtil.getUsernameFromToken(token);
		String mJson = AppUtils.getApplicationName() + StrUtil.COLON + "manager" + StrUtil.COLON + userId;
		TbManager manager = (TbManager) JSONObject.parse(mJson);
		return manager.getName();
	}
}
