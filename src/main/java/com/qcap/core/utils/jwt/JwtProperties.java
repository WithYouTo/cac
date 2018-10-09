package com.qcap.core.utils.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * jwt相关配置
 *
 * 
 * @date 2017-08-23 9:23
 */
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
public class JwtProperties {

	public static final String JWT_PREFIX = "jwt";

	private static String tokenHeader = "access_token";

	private String signatureHeader = "HTTP-X-Whxx-Signature";

	private String secret = "defaultSecret";

	private Long expiration = 3600L;

	private String authPath = "erp.html";

	private String timePath = "time.html";

	private String md5Key = "randomKey";

	public static String getJwtPrefix() {
		return JWT_PREFIX;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Long getExpiration() {
		return expiration;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public String getAuthPath() {
		return authPath;
	}

	public void setAuthPath(String authPath) {
		this.authPath = authPath;
	}

	public String getMd5Key() {
		return md5Key;
	}

	public void setMd5Key(String md5Key) {
		this.md5Key = md5Key;
	}

	/**
	 * @return the timePath
	 */
	public String getTimePath() {
		return timePath;
	}

	/**
	 * @param timePath
	 *            the timePath to set
	 */
	public void setTimePath(String timePath) {
		this.timePath = timePath;
	}

	/**
	 * @return the tokenHeader
	 */
	public static String getTokenHeader() {
		return tokenHeader;
	}

	/**
	 * @param tokenHeader
	 *            the tokenHeader to set
	 */
	public void setTokenHeader(String tokenHeader) {
		JwtProperties.tokenHeader = tokenHeader;
	}

	/**
	 * @return the signatureHeader
	 */
	public String getSignatureHeader() {
		return signatureHeader;
	}

	/**
	 * @param signatureHeader
	 *            the signatureHeader to set
	 */
	public void setSignatureHeader(String signatureHeader) {
		this.signatureHeader = signatureHeader;
	}

}
