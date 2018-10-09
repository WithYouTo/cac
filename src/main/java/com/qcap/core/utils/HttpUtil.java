package com.qcap.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;

/**
 * http请求工具类
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class HttpUtil {

	private static Log log = LogFactory.getLog(HttpUtil.class);

	/**
	 * 获取request将ParamMap参数转换为MAP
	 * 
	 * @Title: getParamMap
	 * @Description: TODO
	 * @param request
	 * @return
	 * @return: Map<String,String>
	 */
	public static Map<String, String> getParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new HashMap<>();
		Map<String, String[]> requestMap = request.getParameterMap();
		Iterator<Entry<String, String[]>> it = requestMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			if (entry.getValue().length == 1) {
				paramMap.put(entry.getKey(), entry.getValue()[0]);
			} else {
				String[] values = entry.getValue();
				String value = "";
				for (String val : values) {
					value = val + ",";
				}
				value = value.substring(0, value.length() - 1);
				paramMap.put(entry.getKey(), value);
			}
		}
		return paramMap;
	}

	/**
	 * map转url请求参数字符串(无序)，value不能为空
	 */
	public final static String map2String(Map<String, Object> map) throws Exception {
		List<String> list = new ArrayList<String>();
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				value = "";
			}
			list.add(key + "=" + value);
		}
		return StringUtils.join(list, "&");
	}

	/**
	 * map转url请求参数字符串(有序)，value不能为空
	 */
	public final static String map2StringSort(Map<String, Object> map) throws Exception {
		List<String> keyList = new ArrayList<String>(map.keySet());
		Collections.sort(keyList);
		List<String> list = new ArrayList<String>();
		for (String key : keyList) {
			Object value = map.get(key);
			if (value == null || "".equals(value)) {
				continue;
			}
			list.add(key + "=" + value);
		}
		return StringUtils.join(list, "&");
	}

	/**
	 * url参数字符串转map
	 */
	public final static Map str2map(String str) throws Exception {
		String[] arr1 = str.split("&");
		Map<String, String> map = new HashMap<String, String>();
		String[] arr2;
		for (String str2 : arr1) {
			arr2 = str2.split("=");
			map.put(arr2[0], arr2.length > 1 ? arr2[1] : "");
		}
		return map;
	}

	/**
	 * map转xml字符串(无序)，value不能为空
	 */
	public final static String map2Xml(Map<String, Object> map) throws Exception {
		StringBuffer sb = new StringBuffer();
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value == null) {
				value = "";
			}
			sb.append("<").append(key).append(">");
			sb.append(value);
			sb.append("</").append(key).append(">");
		}
		return sb.toString();
	}

	/**
	 * map转xml字符串(有序)，value不能为空
	 */
	public final static String map2XmlSort(Map<String, Object> map) throws Exception {
		List<String> list = new ArrayList<String>(map.keySet());
		Collections.sort(list);
		StringBuffer sb = new StringBuffer();
		for (String key : list) {
			sb.append("<").append(key).append(">");
			sb.append(map.get(key) == null ? "" : map.get(key));
			sb.append("</").append(key).append(">");
		}
		return sb.toString();
	}

	/**
	 * js的escape加密
	 */
	public static String escape(String src) {
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (int i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
				tmp.append(j);
			} else if (j < 256) {
				tmp.append("%");
				if (j < 16) {
					tmp.append("0");
				}
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * js的unescape解密
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 发送byte数组
	 */
	public final static String getBytes(String url, byte[] bytes) throws Exception {
		return getBytes(url, bytes, null, 10000, 10000);
	}

	/**
	 * 发起GET请求
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            发送内容
	 * @param headMap
	 *            请求参数
	 * @param cTimeout
	 *            连接等待时间
	 * @param rtimeout
	 *            读取等待时间
	 */
	public final static String getBytes(String url, byte[] content, Map<String, String> headMap, int cTimeout,
			int rtimeout) throws Exception {
		if (content == null) {
			content = "".getBytes(StandardCharsets.UTF_8.displayName());
		}

		URL realUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
		// 发送POST请求必须设置允许输出
		conn.setDoOutput(true);
		// 不使用Cache
		conn.setUseCaches(false);
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(cTimeout);
		conn.setReadTimeout(rtimeout);
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", StandardCharsets.UTF_8.displayName());
		conn.setRequestProperty("Content-type", "application/x-java-serialized-object");
		// 参数
		if (headMap != null) {
			for (Entry<String, String> entry : headMap.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		DataOutputStream send = null;
		try {
			send = new DataOutputStream(conn.getOutputStream());
			send.write(content);
			send.flush();
		} catch (UnknownHostException e) {
			log.error("服务器网络连接出错", e);
			throw e;
		} finally {
			send.close();
		}

		String resultData = "";
		if (HttpStatus.OK.value() == conn.getResponseCode()) {
			InputStream result = null;
			try {
				result = conn.getInputStream();
				resultData = IOUtils.toString(result, StandardCharsets.UTF_8.displayName());
			} catch (Exception e) {
				resultData = "error";
				e.printStackTrace();
			} finally {
				result.close();
			}
		}
		return resultData;
	}

	/**
	 * 发送Map键值对
	 */
	public final static String postMap(String url, Map map) throws Exception {
		String params = map2String(map);
		String contentType = "application/x-www-form-urlencoded";
		return postBytes(url, params.getBytes(StandardCharsets.UTF_8.displayName()), null, contentType, 10000, 10000);
	}

	/**
	 * 发送Map键值对
	 */
	public final static String postMap(String url, Map map, int timeout) throws Exception {
		String params = map2String(map);
		String contentType = "application/x-www-form-urlencoded";
		return postBytes(url, params.getBytes(StandardCharsets.UTF_8.displayName()), null, contentType, timeout,
				timeout);
	}

	/**
	 * 发送InputStream流
	 */
	public final static String postInputStream(String url, InputStream is) throws Exception {
		return postBytes(url, IOUtils.toByteArray(is), null, null, 10000, 10000);
	}

	/**
	 * 发送byte数组
	 */
	public final static String postBytes(String url, byte[] bytes) throws Exception {
		return postBytes(url, bytes, null, null, 10000, 10000);
	}

	/**
	 * 发送byte数组
	 */
	public final static String postBytes(String url, byte[] bytes, int timeout) throws Exception {
		return postBytes(url, bytes, null, null, timeout, timeout);
	}

	/**
	 * 发起POST请求
	 * 
	 * @param url
	 *            请求地址
	 * @param bytes
	 *            发送内容
	 * @param headMap
	 *            请求参数，存放在head请求头中
	 * @param contentType
	 *            content内容类型
	 * @param cTimeout
	 *            连接等待时间
	 * @param rtimeout
	 *            读取等待时间
	 */
	public final static String postBytes(String url, byte[] bytes, Map<String, String> headMap, String contentType,
			int cTimeout, int rtimeout) throws Exception {
		if (bytes == null) {
			bytes = "".getBytes(StandardCharsets.UTF_8.displayName());
		}
		if (StringUtils.isEmpty(contentType)) {
			contentType = "application/x-java-serialized-object";
		}

		URL realUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
		// 发送POST请求必须设置允许输出
		conn.setDoOutput(true);
		// 不使用Cache
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(cTimeout);
		conn.setReadTimeout(rtimeout);

		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", StandardCharsets.UTF_8.toString());
		conn.setRequestProperty("Content-type", contentType);
		// head请求头参数
		if (headMap != null) {
			for (Entry<String, String> entry : headMap.entrySet()) {
				conn.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		DataOutputStream send = null;
		try {
			send = new DataOutputStream(conn.getOutputStream());
			send.write(bytes);
			send.flush();
		} catch (UnknownHostException e) {
			log.error("服务器网络连接出错", e);
			throw e;
		} finally {
			send.close();
		}

		String resultData = "";
		if (HttpStatus.OK.value() == conn.getResponseCode()) {
			InputStream result = null;
			try {
				result = conn.getInputStream();
				resultData = IOUtils.toString(result, StandardCharsets.UTF_8.displayName());
			} catch (Exception e) {
				resultData = "error";
				e.printStackTrace();
			} finally {
				result.close();
			}
		}
		return resultData;
	}

	/**
	 * 代理请求
	 * 
	 * @param url
	 * @param req
	 * @param resp
	 */
	public final static String postProxyHttp(String url, HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		return postProxyHttp(url, req, resp, 60000, 60000);
	}

	/**
	 * 代理请求
	 * 
	 * @param url
	 * @param req
	 * @param resp
	 * @param cTimeout
	 * @param rtimeout
	 */
	public final static String postProxyHttp(String url, HttpServletRequest req, HttpServletResponse resp, int cTimeout,
			int rtimeout) throws Exception {
		URL realUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
		// 发送POST请求必须设置允许输出
		conn.setDoOutput(true);
		// 不使用Cache
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setConnectTimeout(cTimeout);
		conn.setReadTimeout(rtimeout);
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("Charset", StandardCharsets.UTF_8.displayName());
		// 拷贝客户端的head信息作为请求的head参数
		Enumeration<String> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = req.getHeader(key);
			conn.setRequestProperty(key, value);
		}
		// 发送
		InputStream body = null;
		OutputStream send = null;
		try {
			body = req.getInputStream();
			send = conn.getOutputStream();
			IOUtils.copy(body, send);
			send.flush();
		} catch (UnknownHostException e) {
			log.error("服务器网络连接出错", e);
			throw e;
		} finally {
			send.close();
			body.close();
		}

		if (HttpStatus.OK.value() == conn.getResponseCode()) {
			// 取回
			InputStream receive = null;
			OutputStream result = null;
			try {
				receive = conn.getInputStream();
				result = resp.getOutputStream();
				IOUtils.copy(receive, result);
				result.flush();
			} finally {
				result.close();
				receive.close();
			}
		}
		return null;
	}

	public static String doGet(String url, Map<String, String> param) {

		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doGet(String url) {
		return doGet(url, null);
	}

	public static String doPost(String url) {
		return doPost(url, null);
	}

	public static String doPostJson(String url, String json) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}

	public static String doPostShiro(String shiroServerLoginUrl, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(shiroServerLoginUrl);
			httpPost.addHeader("whxxRequestShiro", "yes");
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}

	public static String doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// httpPost.getAllHeaders()
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return resultString;
	}

	/**
	 * face++调用接口
	 * 
	 */
	private final static int CONNECT_TIME_OUT = 30000;
	private final static int READ_OUT_TIME = 50000;
	private static String boundaryString = getBoundary();

	public static byte[] faceplusPost(String url, HashMap<String, String> map, HashMap<String, byte[]> fileMap)
			throws Exception {
		HttpURLConnection conne;
		URL url1 = new URL(url);
		conne = (HttpURLConnection) url1.openConnection();
		conne.setDoOutput(true);
		conne.setUseCaches(false);
		conne.setRequestMethod("POST");
		conne.setConnectTimeout(CONNECT_TIME_OUT);
		conne.setReadTimeout(READ_OUT_TIME);
		conne.setRequestProperty("accept", "*/*");
		conne.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundaryString);
		conne.setRequestProperty("connection", "Keep-Alive");
		conne.setRequestProperty("user-agent", "Mozilla/4.0 (compatible;MSIE 6.0;Windows NT 5.1;SV1)");
		DataOutputStream obos = new DataOutputStream(conne.getOutputStream());
		Iterator iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry) iter.next();
			String key = entry.getKey();
			String value = entry.getValue();
			obos.writeBytes("--" + boundaryString + "\r\n");
			obos.writeBytes("Content-Disposition: form-data; name=\"" + key + "\"\r\n");
			obos.writeBytes("\r\n");
			obos.writeBytes(value + "\r\n");
		}
		if (fileMap != null && fileMap.size() > 0) {
			Iterator fileIter = fileMap.entrySet().iterator();
			while (fileIter.hasNext()) {
				Map.Entry<String, byte[]> fileEntry = (Map.Entry<String, byte[]>) fileIter.next();
				obos.writeBytes("--" + boundaryString + "\r\n");
				obos.writeBytes("Content-Disposition: form-data; name=\"" + fileEntry.getKey() + "\"; filename=\""
						+ encode(" ") + "\"\r\n");
				obos.writeBytes("\r\n");
				obos.write(fileEntry.getValue());
				obos.writeBytes("\r\n");
			}
		}
		obos.writeBytes("--" + boundaryString + "--" + "\r\n");
		obos.writeBytes("\r\n");
		obos.flush();
		obos.close();
		InputStream ins = null;
		int code = conne.getResponseCode();
		try {
			if (code == HttpStatus.OK.value()) {
				ins = conne.getInputStream();
			} else {
				ins = conne.getErrorStream();
			}
		} catch (SSLException e) {
			e.printStackTrace();
			return new byte[0];
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[4096];
		int len;
		while ((len = ins.read(buff)) != -1) {
			baos.write(buff, 0, len);
		}
		byte[] bytes = baos.toByteArray();
		ins.close();
		return bytes;
	}

	private static String getBoundary() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 32; ++i) {
			sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(
					random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_".length())));
		}
		return sb.toString();
	}

	private static String encode(String value) throws Exception {
		return URLEncoder.encode(value, StandardCharsets.UTF_8.name());
	}

}
