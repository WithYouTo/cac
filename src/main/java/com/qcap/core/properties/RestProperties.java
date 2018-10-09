package com.qcap.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: rest自定义参数 以rest开头
 * @package: com.whxx.core.properties
 * @ClassName: RestProperties.java
 * @author: 彭浩
 * @date: 2018/6/4 14:40
 * @version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = RestProperties.REST_PREFIX)
@Getter
@Setter
public class RestProperties {

	public static final String REST_PREFIX = "rest";

	private String coreOpen = "true";

	private String swaggerEnable = "false";

	private String restfulSign = "/rest";

}
