package com.qcap.cac.tools;

import com.qcap.core.common.SettingProperties;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.RedisUtil;
import com.qcap.core.utils.SpringContextHolder;

import cn.hutool.core.util.StrUtil;

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
}
