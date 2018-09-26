package com.qcap.core.utils.sign;

public class SignConfig {
	// 商户密钥
	public static final String KEY_ALGORITHM = "RSA";
	public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
	public static final String PUBLIC_KEY = "publicKey";
	public static final String PRIVATE_KEY = "privateKey";
	public static final int KEY_SIZE = 2048;

	public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
	public static final String ENCODE_ALGORITHM = "SHA-256";
	public static String CHARSET = "UTF-8";
}
