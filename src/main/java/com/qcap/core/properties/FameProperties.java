package com.qcap.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = FameProperties.FAME_PREFIX)
public class FameProperties {
	
	public static final String FAME_PREFIX = "cac";

	private String swaggerEnable = "false";

	private String restfulSign = "/rest";
	
	private String payCallBack="/payCallBack";

	private String[] anonPath ;

	public String[] getAnonPath() {
		return anonPath;
	}

	public void setAnonPath(String[] anonPath) {
		this.anonPath = anonPath;
	}

	/**
	 * @return the swaggerEnable
	 */
	public String getSwaggerEnable() {
		return swaggerEnable;
	}

	/**
	 * @param swaggerEnable the swaggerEnable to set
	 */
	public void setSwaggerEnable(String swaggerEnable) {
		this.swaggerEnable = swaggerEnable;
	}

	/**
	 * @return the restfulSign
	 */
	public String getRestfulSign() {
		return restfulSign;
	}

	/**
	 * @param restfulSign the restfulSign to set
	 */
	public void setRestfulSign(String restfulSign) {
		this.restfulSign = restfulSign;
	}

	/**
	 * @return the payCallBack
	 */
	public String getPayCallBack() {
		return payCallBack;
	}

	/**
	 * @param payCallBack the payCallBack to set
	 */
	public void setPayCallBack(String payCallBack) {
		this.payCallBack = payCallBack;
	}

}
