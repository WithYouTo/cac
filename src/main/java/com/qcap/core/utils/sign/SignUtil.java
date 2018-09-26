package com.qcap.core.utils.sign;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SignUtil {

	private final static Logger logger = LoggerFactory.getLogger(SignUtil.class);

	public static void main(String[] args) {
		// try {
		// String url1 =
		// "iklh2xklvCx8m1p4E726UoUvzzbu8iam5yBkUuLzMRZHrvOMJ3Sgb4GG0jVFgwlqmfyyRKBWaCQMN5wy5A5ApxsFqv1VHauUoPcVQhoLrLrrv8VGqAz4oWsyRSp2K5rYS0S7ZJKCXdM5TAgqQGLlN/RN88s4pLao/89hMFigyBmfS7BSlyf+K4H5CgO+mYxzL74xkB5MgKPTje6o4nP+aGSfMgXJoEr5nZZhtZqMNAt6bLB1djb0KmaEKJG/glfB6HoY/H1IrIBH2fce6N+bmRehHLuZ6zW9CrrtWqTP3I1hWumiUqAZViBhuLOJE5q2CZMMvbqm/wAPEMwr8E6Jcw==";
		// String url2 =
		// "iklh2xklvCx8m1p4E726UoUvzzbu8iam5yBkUuLzMRZHrvOMJ3Sgb4GG0jVFgwlqmfyyRKBWaCQMN5wy5A5ApxsFqv1VHauUoPcVQhoLrLrrv8VGqAz4oWsyRSp2K5rYS0S7ZJKCXdM5TAgqQGLlN%2FRN88s4pLao%2F89hMFigyBmfS7BSlyf%2BK4H5CgO%2BmYxzL74xkB5MgKPTje6o4nP%2BaGSfMgXJoEr5nZZhtZqMNAt6bLB1djb0KmaEKJG%2FglfB6HoY%2FH1IrIBH2fce6N%2BbmRehHLuZ6zW9CrrtWqTP3I1hWumiUqAZViBhuLOJE5q2CZMMvbqm%2FwAPEMwr8E6Jcw%3D%3D";
		// System.out.println(UrlEncoderUtils.isUrlEncoded(url1));
		// System.out.println(UrlEncoderUtils.isUrlEncoded(url2));
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		// 公私钥对
		// try {
		// RSA.createKeyPairs();
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Map<String, String> keyMap = RSA.generateKeyToString();
		// String publicKey = keyMap.get(SignConfig.PUBLIC_KEY);
		// logger.debug("【" + publicKey + "】");
		// String privateKey = keyMap.get(SignConfig.PRIVATE_KEY);
		// logger.debug("【" + privateKey + "】");

		try {
			String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoD2eaaw4n4kMWIkeakO/fgtzDVzn2Muvnbx+NcVlVNfA0oRoZ0ANOaVVeZh26Swn3Yn9rxg23RbO2Gij1Aw3UO3hX5OwkPZGL3lMpfpW1DzNmbO8dMBcv+hUyJlvlTWtNTH1sG3+Vz2Ap/MdmkXBqjk9E6+yV+TC/6k+ipX7gKQHiqdayAneJM2zkCm9JaN2zhfa+FrlYAq0UuffIkvk5oXz4t7MVV8ncBq6IFsbDivwmjhiLcyi57KQFLuYasrvdNhESIfBiGc3u3ZQs3TmEc9grUTeHZC1CY12JGBSR7Ay5cLQru3tQ8EmDslsgg3Ba5cz574aLeIHGy+zUWXZpwIDAQAB";
			// String publicKey =
			// "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl7ji8f+/UxRf9qmGHO5PLgbr0sC4IO+BPWBEqQcFaPkN2WJQIjNecCAqhKp6Hf/Rwr//MF4kCBnb8QSkbZxyYO2u6zulLW1J3vR/38XHb5ETB+ap8Pw6NcriD/o9xdXm1Mn3uqeHprskKTnIj/7sq/enDAG9yuPv0QTj6TETALiVtGwmoQRV0PJqHWNDnP9BYYRbKJoESC2kXnDh+SKIHmsjqCXBluLB8J+fzo+T26pqkAVPWn761L05qlc0nFgJZAA7S316xR0Cwh3agXhJkRFWLcXhDMypFHnXkJkLIYkVVYTwMU4Ug0LgP7pMvUaOdBwO/TeBIpRgE1XAEv3SmwIDAQAB";
			// String privateKey =
			// "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXuOLx/79TFF/2qYYc7k8uBuvSwLgg74E9YESpBwVo+Q3ZYlAiM15wICqEqnod/9HCv/8wXiQIGdvxBKRtnHJg7a7rO6UtbUne9H/fxcdvkRMH5qnw/Do1yuIP+j3F1ebUyfe6p4emuyQpOciP/uyr96cMAb3K4+/RBOPpMRMAuJW0bCahBFXQ8modY0Oc/0FhhFsomgRILaRecOH5IogeayOoJcGW4sHwn5/Oj5PbqmqQBU9afvrUvTmqVzScWAlkADtLfXrFHQLCHdqBeEmREVYtxeEMzKkUedeQmQshiRVVhPAxThSDQuA/uky9Ro50HA79N4EilGATVcAS/dKbAgMBAAECggEAHD5mwPYC/8xDMP/N34TdaIwRSmJl5Huxa2OIZI7SCwCR+225zSQS6HyUba3unbcJrV9gefiMbJWjw/JBScZd5H1amp+32AUIQ2DwjQ+cWMCA8m+WIy5RB8KE4sSKy1FObz5Jx414Syl7SdFf7K5gEINBrZKFakeUVSy74KUqDHAFeR6Q8vKMTMOVZhpkrhfmfbw4YOiZukYL22KC/FubXS+mUk1bVXnQF714YJ3XDWWt7GEkDFRzCLkUU2pHc2Ggsltr7gJIpy6Vy0ZDc/YUmfzPpIQEem2XwefN5+usXkICGIUCGW1Q6JfWI9cDvmyWvWQHpG+FSbBVbAm7MBR1gQKBgQDuYMq53iX8/mQNY9ZckHZYuXMTnh4bseHerXTP9wXi4rUEuKucmS4Uv9/aViyaWom6l8pjNtDmhiL8dGewQjmAmrOU2FYwcGO4R5UvwG4PzKOthX4VZ0hzoNw7StEud1LKBjqq7Nv0PRXRuN6bIVo+dKLrCuHqr5SHPIG5vMKAuQKBgQCi8CrdtIa8Nxyra37pOJaT7q7yqNkFO3vSa1pyY4FfYhcTT8G0y5KmcW4ESxl0e/4JZoolxX7A74Cd+gkkC128PQrhrXEDSqrq7BiOrFwiQ6SvgxrJKUACGCLm+uDI0NsiS/OYZTFHmWxesOKVy+02GscYBKZL49twKkbdAMs78wKBgD8ELXN3S5mW8oIrq2Zq9i48UDSWbyUpICnKtv+nMNIq1mm6G/vY7rqjgpzofQANAS3npKQXwJ/ARA1zBFovF55sxx6JFjfV7jawDbtC5pWcpoRfrtYfqKKQmse93pCnEKbBgM7gsMDfSR5Y/dgKuB2cndj+1nxBEEWz9CNSyCRZAoGAXsBpPRpBC+zEqE7Ps5IrxoUTiZt08J0ByYCSTVjeH/ir0Jgd3iRP+KiXwLlnaRy9wvu1NHff2RSt57BCZoqRjOi2jjxNLwvZztlTAozq2yYY+9Oy3O43AcDFcyrLUxxZgl1GoAWgnbFzmrEkYtX+8OqrgqUZi6JKtrRvFw/iH88CgYEA6shpYrmXlGS27nLr/VZ5AfunaJ2iUbq4FHBJmcMa5hTdl8cQKlVW6CAieKWC0SDakOmL/G/EK3+bTKOqO4oPqp5MtvhxQ6CD7wvic/t66NxYsoRvWSmfgzm51nARxUwCO7Pz9Ek/gxmIyd3ovc1eP/T/gUAPFkDiYlhpag0mgLk=";
			String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCgPZ5prDifiQxYiR5qQ79+C3MNXOfYy6+dvH41xWVU18DShGhnQA05pVV5mHbpLCfdif2vGDbdFs7YaKPUDDdQ7eFfk7CQ9kYveUyl+lbUPM2Zs7x0wFy/6FTImW+VNa01MfWwbf5XPYCn8x2aRcGqOT0Tr7JX5ML/qT6KlfuApAeKp1rICd4kzbOQKb0lo3bOF9r4WuVgCrRS598iS+TmhfPi3sxVXydwGrogWxsOK/CaOGItzKLnspAUu5hqyu902ERIh8GIZze7dlCzdOYRz2CtRN4dkLUJjXYkYFJHsDLlwtCu7e1DwSYOyWyCDcFrlzPnvhot4gcbL7NRZdmnAgMBAAECggEAVZOS3myTcOAIL+BK1gWIZxBGSYSsbSOajmr0HFNnYoA3Ajakaz/R0E9z7pR2LODgCXiAeto7sqdToE+B7uT3RclE6348da6QnRnyzVF8vawbQVKz+sYgwnyZiugmp1EbR6V+yNJ1WrIF26wCyzklzHr1G2ZhoH8ThOYl+G1Q+8nDhaqJwcZwXzMGw1MYN3h0rP5vz05gqpvQSRHZS2OwFbaxDM053GanczEJ/y83ltiCPRc/gPO1alGDU5O+C6UbXQ0oLEqGUqiS8+PyOl+MUGJOsGFX1dgKHFXYnvaEZgYhFHzNnWykB2zwfcT67rI6xywkaKlc9/eQwbyii86CgQKBgQDp/sid9kLmc3oXge1pyR1vrmVLr97vgieuAh7xW93vNjfflvMnRKgWjyHKYlPaUB3/2/gfEK1DBVZ0FPXj5cnr2pKbG1K0aeis/j0HiAZh9wnkZLrlcBuJzzVMD8CEQGhc1j2/2uB/JJaWGsxjBJsBnT8XKTw1ydB24RJPmko4KQKBgQCvT0Nx471bAjocvZbhPWOXUlkdX1FrtRHQn0uh5vxMsG5UZxVvC3HusWWPQCe8v5M6XyeJGGr8qI42/tfGMgH1IkFo29pGxrsfKEEj94Q8A/YAJ6dYD9I098FlNA1+l2H/pSgDPJnak9YvcXYoEOzMnEOMLcJkaRFS8/mqGs79TwKBgQCdTWteTYOx2mV0UiLhH5NLVcjA9cz/49Z68vAMPz7WJfOvupFhj5UT/Uqw/CL0uzE/pIeLkrF3QapZxRF8ECkLBdfSr66MnDoPuaYbXwUzbFhZEqJVxWSt4v8oT9qSzZ2a82d1h1WuA1G8PO2zjxAJKMWV82GHo1Eh6bJekxFb6QKBgApzfD7W59v8FwDNoKgO49gKlbt1ikIUilpYlMazIYlk3z7y4kk7cnBbbQWMOpI7DYjmUVIP/ZjQZ6IJsU1kf89gWmvo52tev5sM6E0/nUeCqPC7bHOIheyYdJQW9VL9rV7zNp5yx21LcPJlHjknQSa1jiVWOQeJZ/n9B35HmN1rAoGBAJxW/kJ2O3a3BDQwHz62tNDqHHQ+q3nA7ZXgeLLW1sqW7RTZDrOxsXt/dgHn6TDeG/J7X0ubaZvDfJHiVDE84oheDtUj2lfNXlzcaI8tdXdp03r86syHCVQeRBdfGxzhkv49nEUdngvBmiD4iJCVx1JfoDqcFH4y+Cfuh1YTBT/D";
			Map<String, String> param = new HashMap<String, String>();
			// param.put("deviceInfo", "10880110000t");
			// param.put("goodsName", "幼教面缴-test");
			// param.put("inSysCode", "whxxcepftf");
			// param.put("mchOrderNo", "YJ012017112113430584");
			// param.put("notifyUrl",
			// "http%3A%2F%2Fweizantest.whmodern.cn%2Fpayment%2Fwhpay_test%2Fnotify_zfb.php");
			// param.put("payPlatform", "A");
			// param.put("payScene", "MICROPAY");
			// param.put("r", "15112430749384");
			// param.put("spbillCreateIp", "");
			param.put("deviceCode", "22222");
//			param.put("payTime", "2018-04-01 01:02:26");
//			param.put("totalFee", "0.01");
//			param.put("mchOrderNo", "WG012018040101020615225157263838");
//			param.put("tradeStatus", "SUCCESS");
//			param.put("payPlatform", "W");
//			param.put("wpayNo", "163530000156201804014140259571");
			String signStr = sign(param, privateKey);
			System.out.println("【" + signStr + "】");
			param.put("sign", URLEncoder.encode(signStr, "utf-8"));
			//param.put("sign", URLEncoder.encode("XulsgV4mQF7fwnZWyDFFmbCa6UXsc+sNF/8P+zFaRAp8QYXNVWCkdpczAlYsWLTMOV824pjFEvGXZBK+Soe1X64Nm2F6Hu3jPuKDKEEXBT7jjPAAll9x7E9s+BU7JSlqGV4mqscgL3+S40/hhc8Q80eUKfXMZQeqM1+8BjGdPz1GTlGk+OvldTScHzHtKOkr9tXFv44TLsFIcJaR/DzwZMxc+3/IvwKz/pHYcCDKXmw2eb2wcSRUkNyF+16iZGxSC/sEAhtYThZg2n21WfpZiLSCNEdk37yieAyPzg1EubPkNVuRnsOXmp9f53y5MHc7hvGWxzrdYtT/Uuhb9cerNw==","utf-8"));// // // param.put("sign",
			// // //
			// //
			// "DyDz69wtE2U3Ur+Ae2dvLVGaZ+lZCP9HdHd+OY5BCiGboWBp/68AaeIxWSM3Ft3bINojUbVAWARlDh4dJfh6JwclC8Mg1trGl0ZVY/HNGx30Z1fwJA47HB/2pl4w3zP5t1OcL9f7NuQkrfhHm0CefyG/k8hrkoFU9vYnPCVoQstzeK6u1L2Mb8wo7FUodKiMQVT4bP1GYoTiMqcSdzLV8TIFC0AU9BLWZRTvuuSP1E5rCAOufyFUOQVZ749w6xdFiElW6jTm6D2LcGDXVcvC2L6nN6QWv/D8u28Ksha2nHfo0WSRbadXQbSGy+ucIMwGgY6Mg1uRTh1r1qgpg91apw==");
//			System.out.println("====param===" +param );
			if (ifJValidSignature(param, publicKey)) {
				System.out.println("验签成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 对参数签名：
	 * 对reqData所有请求参数按从a到z的字典顺序排列，如果首字母相同，按第二个字母排列，以此类推。排序完成后按将所有键值对以“&”符号拼接。
	 * 拼接完成后再加上商户密钥。示例：param1=value1&param2=value2&...&paramN=valueN&secretKey
	 * 
	 * @param reqDataMap
	 *            请求参数
	 * @param secretKey
	 *            商户密钥
	 */
	public static String sign(Map<String, String> reqDataMap, String secretKey) {
		StringBuffer buffer = new StringBuffer();
		List<String> keyList = sortParams(reqDataMap);
		for (String key : keyList) {
			buffer.append(key).append("=").append(reqDataMap.get(key)).append("&");
		}
		String bufferStr = buffer.substring(0, buffer.length() - 1);
		logger.info("加签参数【" + bufferStr + "】");

		try {
			PrivateKey privateKey = RSA.restorePrivateKey(Base64.getDecoder().decode(secretKey));
			byte byteBuffer[] = sign(privateKey, bufferStr);

			// return bytesToHexString(byteBuffer);
			return Base64.getEncoder().encodeToString(byteBuffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 签名
	 * 
	 * @param privateKey
	 *            私钥
	 * @param plain_text
	 *            明文
	 * @return
	 */
	public static byte[] sign(PrivateKey privateKey, String plain_text) {
		MessageDigest messageDigest;
		byte[] signed = null;
		try {
			messageDigest = MessageDigest.getInstance(SignConfig.ENCODE_ALGORITHM);
			messageDigest.update(plain_text.getBytes());
			byte[] outputDigest_sign = messageDigest.digest();
			String plain_text_temp = bytesToHexString(outputDigest_sign);
			Signature Sign = Signature.getInstance(SignConfig.SIGNATURE_ALGORITHM);
			Sign.initSign(privateKey);
			Sign.update(plain_text_temp.getBytes());
			signed = Sign.sign();
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		return signed;
	}

	/**
	 * java不需要解码
	 * @Title: isJValidSignature 
	 * @Description: TODO
	 * @param reqDataMap
	 * @param secretKey
	 * @return
	 * @return: boolean
	 */
	public static boolean ifJValidSignature(Map<String, String> reqDataMap, String secretKey) {
		StringBuffer buffer = new StringBuffer();
		try {
			String signStr = reqDataMap.get("sign");
			String notifyUrl = reqDataMap.get("notifyUrl");
			String returnUrl = reqDataMap.get("returnUrl");
			if (StringUtils.isNotBlank(notifyUrl) && !UrlEncoderUtils.isUrlEncoded(notifyUrl)) {
				reqDataMap.put("notifyUrl", URLEncoder.encode(notifyUrl, "utf-8"));
			}
			if (StringUtils.isNotBlank(returnUrl) && !UrlEncoderUtils.isUrlEncoded(returnUrl)) {
				reqDataMap.put("returnUrl", URLEncoder.encode(returnUrl, "utf-8"));
			}
			if (StringUtils.isBlank(signStr)) {
				return false;
			}
			if (UrlEncoderUtils.isUrlEncoded(signStr)) {
				logger.info("待UrlEncoder字符串【" + signStr + "】");
				signStr = URLDecoder.decode(signStr, "utf-8");
			}
			reqDataMap.remove("sign");

			List<String> keyList = sortParams(reqDataMap);
			for (String key : keyList) {
				buffer.append(key).append("=").append(reqDataMap.get(key)).append("&");
			}
			String bufferStr = buffer.substring(0, buffer.length() - 1);
			logger.info("加签参数【" + bufferStr + "】");

			PublicKey publicKey = RSA.restorePublicKey(Base64.getDecoder().decode(secretKey));

			// return verifySign(publicKey, bufferStr,
			// hexStringToBytes(signStr));
			logger.info("待验字符串【" + signStr + "】");
			return verifySign(publicKey, bufferStr, Base64.getDecoder().decode(signStr));
			// return RSA1.doCheck(bufferStr, signStr, secretKey,
			// SignConfig.CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * c++不需要解码
	 * @Title: isCValidSignature 
	 * @Description: TODO
	 * @param reqDataMap
	 * @param secretKey
	 * @return
	 * @return: boolean
	 */
	public static boolean ifCValidSignature(Map<String, String> reqDataMap, String secretKey) {
		StringBuffer buffer = new StringBuffer();
		try {
			String signStr = reqDataMap.get("sign");
			String notifyUrl = reqDataMap.get("notifyUrl");
			String returnUrl = reqDataMap.get("returnUrl");
			if (StringUtils.isNotBlank(notifyUrl) && !UrlEncoderUtils.isUrlEncoded(notifyUrl)) {
				reqDataMap.put("notifyUrl", URLEncoder.encode(notifyUrl, "utf-8"));
			}
			if (StringUtils.isNotBlank(returnUrl) && !UrlEncoderUtils.isUrlEncoded(returnUrl)) {
				reqDataMap.put("returnUrl", URLEncoder.encode(returnUrl, "utf-8"));
			}
			if (StringUtils.isBlank(signStr)) {
				return false;
			}
			if (!UrlEncoderUtils.isUrlEncoded(signStr)) {
				logger.info("待UrlEncoder字符串【" + signStr + "】");
				signStr = URLDecoder.decode(signStr, "utf-8");
			}
			reqDataMap.remove("sign");

			List<String> keyList = sortParams(reqDataMap);
			for (String key : keyList) {
				buffer.append(key).append("=").append(reqDataMap.get(key)).append("&");
			}
			String bufferStr = buffer.substring(0, buffer.length() - 1);
			logger.info("加签参数【" + bufferStr + "】");

			PublicKey publicKey = RSA.restorePublicKey(Base64.getDecoder().decode(secretKey));

			// return verifySign(publicKey, bufferStr,
			// hexStringToBytes(signStr));
			logger.info("待验字符串【" + signStr + "】");
			return verifySign(publicKey, bufferStr, Base64.getDecoder().decode(signStr));
			// return RSA1.doCheck(bufferStr, signStr, secretKey,
			// SignConfig.CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 验签
	 * 
	 * @param publicKey
	 *            公钥
	 * @param plain_text
	 *            明文
	 * @param signed
	 *            签名
	 */
	public static boolean verifySign(PublicKey publicKey, String plain_text, byte[] signed) {
		MessageDigest messageDigest;
		boolean SignedSuccess = false;
		try {
			messageDigest = MessageDigest.getInstance(SignConfig.ENCODE_ALGORITHM);
			messageDigest.update(plain_text.getBytes());
			byte[] outputDigest_verify = messageDigest.digest();
			String plain_text_temp = bytesToHexString(outputDigest_verify);
			Signature verifySign = Signature.getInstance(SignConfig.SIGNATURE_ALGORITHM);
			verifySign.initVerify(publicKey);
			verifySign.update(plain_text_temp.getBytes());
			SignedSuccess = verifySign.verify(signed);
		} catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
			e.printStackTrace();
		}
		return SignedSuccess;
	}

	/**
	 * 对参数按字典顺序排序，不区分大小写
	 */
	public static List<String> sortParams(Map<String, String> reqDataMap) {
		List<String> list = new ArrayList<String>(reqDataMap.keySet());
		Collections.sort(list, new Comparator<String>() {
			public int compare(String o1, String o2) {
				String[] temp = { o1.toLowerCase(), o2.toLowerCase() };
				Arrays.sort(temp);
				if (o1.equalsIgnoreCase(temp[0])) {
					return -1;
				} else if (temp[0].equalsIgnoreCase(temp[1])) {
					return 0;
				} else {
					return 1;
				}
			}
		});
		return list;
	}

	/**
	 * bytes[]换成16进制字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 16进制字符串换成bytes[]
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	/**
	 * SHA1
	 * 字符串加密算法
	 * @param str
	 * @return
	 */
	public static String getSha1(String str){
		//十六进制数组
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	            'A', 'B', 'C', 'D', 'E', 'F'};
		//加密之后的算法
		String changeStr = "";
		try {
	    	 if (null == str || 0 == str.length()){
	 	        return null;
	 	    }
	 	    logger.info("===原始串==" + str); 
	 	    
	        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	        mdTemp.update(str.getBytes("UTF-8"));
	      
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char[] buf = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	            byte byte0 = md[i];
	            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            buf[k++] = hexDigits[byte0 & 0xf];
	        }
	        changeStr = new String(buf);
	        logger.info("===SHA1加签串==" + changeStr); 
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
	    return  changeStr;
	}
	
	public static boolean isValidSignature(Map<String, String> reqDataMap, String secretKey) {
		StringBuffer buffer = new StringBuffer();
		try {
			String signStr = reqDataMap.get("sign");
			String notifyUrl = reqDataMap.get("notifyUrl");
			String returnUrl = reqDataMap.get("returnUrl");
			if (StringUtils.isNotBlank(notifyUrl) && !UrlEncoderUtils.isUrlEncoded(notifyUrl)) {
				reqDataMap.put("notifyUrl", URLEncoder.encode(notifyUrl, "utf-8"));
			}
			if (StringUtils.isNotBlank(returnUrl) && !UrlEncoderUtils.isUrlEncoded(returnUrl)) {
				reqDataMap.put("returnUrl", URLEncoder.encode(returnUrl, "utf-8"));
			}
			if (UrlEncoderUtils.isUrlEncoded(signStr)) {
				logger.info("待UrlEncoder字符串【" + signStr + "】");
				signStr = URLDecoder.decode(signStr, "utf-8");
			}
			reqDataMap.remove("sign");

			List<String> keyList = sortParams(reqDataMap);
			for (String key : keyList) {
				buffer.append(key).append("=").append(reqDataMap.get(key)).append("&");
			}
			String bufferStr = buffer.substring(0, buffer.length() - 1);
			logger.info("加签参数【" + bufferStr + "】");

			PublicKey publicKey = RSA.restorePublicKey(Base64.getDecoder().decode(secretKey));

			// return verifySign(publicKey, bufferStr,
			// hexStringToBytes(signStr));
			logger.info("待验字符串【" + signStr + "】");
			return verifySign(publicKey, bufferStr, Base64.getDecoder().decode(signStr));
			// return RSA1.doCheck(bufferStr, signStr, secretKey,
			// SignConfig.CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
