package com.qcap.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = RestProperties.REST_PREFIX)
public class RestProperties {
	
	 public static final String REST_PREFIX = "rest";
	 
	 private String filePath = "";
		
	 private String restfulSign = "rest";
	 
	public String getRestfulSign() {
		return restfulSign;
	}

	public void setRestfulSign(String restfulSign) {
		this.restfulSign = restfulSign;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

		public static String getRestPrefix() {
		return REST_PREFIX;
	}


}
