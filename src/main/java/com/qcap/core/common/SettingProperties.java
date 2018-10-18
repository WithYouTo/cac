package com.qcap.core.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SettingProperties {

	// 服务器类型（开发、测试、正式）
	public static String serviceType;

	@Value("${spring.profiles.active}")
	public void setServiceType(String serviceType) {
		SettingProperties.serviceType = serviceType;
	}
}
