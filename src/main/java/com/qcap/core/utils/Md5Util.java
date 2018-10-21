package com.qcap.core.utils;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @author xx
 */
public class Md5Util {

	private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	private final static int DIGIT = 32;

	/**
	 * 获取48位随机salt
	 * 
	 * @Title: getSalt
	 * @return
	 * @return: String
	 */
	public static String getSalt() {

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < DIGIT; i++) {
			sb.append(HEX_DIGITS[new Random().nextInt(HEX_DIGITS.length)]);
		}
		return sb.toString();
	}

	/**
	 * 根据明文和salt经MD5加密得到密文
	 * 
	 * @param password
	 *            密码
	 * @param salt
	 *            盐值
	 * @return String
	 */
	public static String encrypt(String password, String salt) {
		String result = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 加密后的字符串
			result = byteArrayToHexString(md.digest(mergePasswordAndSalt(password, salt).getBytes("UTF-8")));
		} catch (Exception ex) {
			result = "";
		}
		return result;
	}

	/**
	 * 验证密码
	 * 
	 * @param encPass
	 *            密文
	 * @param rawPass
	 *            明文
	 * @param salt
	 *            混淆值
	 * @return true:正确 false:错误
	 */
	public static boolean isPasswordValid(String encPass, String rawPass, String salt) {
		String pass = encrypt(rawPass, salt);
		return encPass.equals(pass);
	}

	private static String mergePasswordAndSalt(String password, String salt) {

		if (password == null) {
			password = "";
		}
		if ((salt == null) || "".equals(salt)) {
			return password;
		} else {
			return password + "{" + salt + "}";
		}
	}

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuilder resultSb = new StringBuilder();
		for (Byte bt : b) {
			resultSb.append(byteToHexString(bt));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}

	public static void main(String[] args) {

		String salt = getSalt();
		System.out.println("salt==" + salt);
		String rawPass = "passwordadmin";
		String encPass = encrypt(rawPass, salt);
		System.out.println("密文==" + encPass);

		System.out.println(isPasswordValid(encPass, rawPass, salt));

	}
}