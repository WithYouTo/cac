package com.qcap.cac.tools;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class JpushTools {

	public static final Logger logger = LoggerFactory.getLogger(JpushTools.class);

	// 广播（即全平台、全目标推送）
	public static PushResult broadcast(String msg) {
		String masterSecret = RedisTools.getCommonConfig("CAC_MASTER_SECRET_KEY");
		String appKey = RedisTools.getCommonConfig("CAC_APP_KEY");
		// 广播
		return JpushTools.broadcast(masterSecret, appKey, msg);
	}

	// 向单一目标推送
	public static PushResult pushSingle(String userNo, String msg) {
		String masterSecret = RedisTools.getCommonConfig("CAC_MASTER_SECRET_KEY");
		String appKey = RedisTools.getCommonConfig("CAC_APP_KEY");
		// 发送单一目标
		return JpushTools.pushSingle(masterSecret, appKey, userNo, msg);
	}

	// 向多个目标推送
	public static PushResult pushArray(List<String> userArr, String msg) {
		String masterSecret = RedisTools.getCommonConfig("CAC_MASTER_SECRET_KEY");
		String appKey = RedisTools.getCommonConfig("CAC_APP_KEY");
		// 发送多个目标
		return JpushTools.pushArray(masterSecret, appKey, userArr, msg);
	}

	// 广播（即全平台、全目标推送）
	public static PushResult broadcast(String masterSecret, String appKey, String alert) {
		// 创建推送连接
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());

		// 构建推送对象
		PushPayload payload = buildPushObject_all_all_alert(alert);

		try {
			// 推送极光服务器
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
			return result;
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
		return null;
	}

	// 向单一目标推送
	public static PushResult pushSingle(String masterSecret, String appKey, String alias, String alert) {
		// 创建推送连接
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());

		// 构建推送对象
		PushPayload payload = buildPushObject_all_alias_alert(alias, alert);

		try {
			// 推送极光服务器
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
			return result;
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
		return null;
	}

	// 向多个目标推送
	public static PushResult pushArray(String masterSecret, String appKey, List<String> aliasArray, String alert) {
		// 创建推送连接
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());

		// 构建推送对象
		PushPayload payload = buildPushObject_all_aliasArray_alert(aliasArray, alert);

		try {
			// 推送极光服务器
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
			return result;
		} catch (APIConnectionException e) {
			logger.error("Connection error, should retry later", e);
		} catch (APIRequestException e) {
			logger.error("Should review the error, and fix the request", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
		}
		return null;
	}

	// 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知
	private static PushPayload buildPushObject_all_all_alert(String alert) {
		return PushPayload.alertAll(alert);
	}

	// 所有平台，推送目标是别名为 alias(单个)，通知内容为 alert
	private static PushPayload buildPushObject_all_alias_alert(String alias, String alert) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
				.setNotification(Notification.alert(alert)).build();
	}

	// 所有平台，推送目标是别名为 aliasArray(多个)，通知内容为 alert
	private static PushPayload buildPushObject_all_aliasArray_alert(List<String> aliasArray, String alert) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(aliasArray))
				.setNotification(Notification.alert(alert)).build();
	}

}
