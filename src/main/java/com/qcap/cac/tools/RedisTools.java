package com.qcap.cac.tools;

import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.RedisUtil;

import cn.hutool.core.util.StrUtil;

public class RedisTools {

	public static void setCommonConfig(RedisUtil redisUtil, String type, String key, String value) {
		key = type + StrUtil.COLON + key;
		redisUtil.setNotExpired(AppUtils.getApplicationName() + StrUtil.COLON + key, value);
	}

	public static String getCommonConfig(RedisUtil redisUtil, String type, String key) {
		key = type + StrUtil.COLON + key;
		return redisUtil.get(AppUtils.getApplicationName() + StrUtil.COLON + key);
	}
}
