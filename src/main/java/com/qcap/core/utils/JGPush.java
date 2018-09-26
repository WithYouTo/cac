package com.qcap.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JGPush {
	protected static final Logger logger = LoggerFactory.getLogger(JGPush.class);

	public static final String TITLE = "申通快递";
	public static final String ALERT = "祝大家新春快乐";
	public static final String MSG_CONTENT = "申通快递祝新老客户新春快乐";
	public static final String REGISTRATION_ID = "0900e8d85ef";
	public static final String TAG = "tag_api";

	public static JPushClient jpushClient = null;

	//所有平台的所有用户全部推送
	public static void testSendPush(String appKey, String masterSecret) {
		jpushClient = new JPushClient(masterSecret, appKey, 3);

		//生成推送的内容，这里我们先测试全部推送  
		PushPayload payload = buildPushObject_all_alias_alert();
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);

		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
		}
	}

	//向个人推送
	public static void sendPushToOne(String appKey, String masterSecret, String alias, String alert, String flag) {

		jpushClient = new JPushClient(masterSecret, appKey, 3);
		PushPayload payload = buildPushObject_android_and_ios(alias, alert, flag);
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
		}
	}

	public static void sendPushToOneIos(String appKey, String masterSecret, String alias, String alert, String flag) {

		jpushClient = new JPushClient(masterSecret, appKey, 3);
		PushPayload payload = buildPushObject_ios(alias, alert, flag);
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("result: " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
		}
	}

	public static void sendPushToOneAn(String appKey, String masterSecret, String alias, String alert, String flag) {

		jpushClient = new JPushClient(masterSecret, appKey, 3);
		PushPayload payload = buildPushObject_android(alias, alert, flag);
		try {
			PushResult result = jpushClient.sendPush(payload);
			logger.info("Got result - " + result);
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Code: " + e.getErrorCode());
			logger.info("Error Message: " + e.getErrorMessage());
			logger.info("Msg ID: " + e.getMsgId());
		}
	}

	//    public static PushPayload buildPushObject_all_all_alert() {  
	//        return PushPayload.alertAll(ALERT);  
	//    }  

	//所有平台的所有用户全部推送
	public static PushPayload buildPushObject_all_alias_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.all())//设置接受的平台  
				.setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到  
				.setNotification(Notification.alert(ALERT)).build();
	}

	//推送给android和iOS的特定用户（别名为手机号码）
	public static PushPayload buildPushObject_android_and_ios(String alias, String alert, String flag) {
		//		TODO 2018.3.9 胡智超修改

		/*	return PushPayload.newBuilder()
					//                .setPlatform(Platform.android())  
					.setPlatform(Platform.android_ios())
					.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias)).build())
					//                .setNotification(Notification.alert(alert)) 
					.setNotification(Notification.newBuilder().setAlert(alert)
							.addPlatformNotification(AndroidNotification.newBuilder().addExtra("from", flag).build())
							//                        .addPlatformNotification(IosNotification.newBuilder()  
							//                                .incrBadge(1)  
							//                                .addExtra("from", flag).build())  
							.addPlatformNotification(IosNotification.newBuilder()
									//                              .setAlert(ALERT)  
									.setBadge(1).setSound("happy").addExtra("from", flag).build())
							.build())
					.build();*/
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias)).build())
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(
								AndroidNotification.newBuilder().addExtra("from", "1").setAlert(alert).build())
						.addPlatformNotification(IosNotification.newBuilder().setAlert(alert).setBadge(1)
								.setSound("happy").addExtra("from", "1").build())
						.build())
				.setMessage(Message.newBuilder().setMsgContent("").addExtra("from", "JPush").build())
				.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
	}

	public static PushPayload buildPushObject_android(String alias, String alert, String flag) {
		return PushPayload.newBuilder().setPlatform(Platform.android())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias)).build())
				//                .setNotification(Notification.alert(alert)) 
				.setNotification(Notification.newBuilder().setAlert(alert)
						.addPlatformNotification(AndroidNotification.newBuilder().addExtra("from", flag).build())
						.build())
				.build();
		//                        .addPlatformNotification(IosNotification.newBuilder()  
		//                                .incrBadge(1)  
		//                                .addExtra("from", flag).build())  
	}

	//只推送给iOS的特定用户（别名为手机号码）
	public static PushPayload buildPushObject_ios(String alias, String alert, String flag) {
		return PushPayload.newBuilder().setPlatform(Platform.ios())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.alias(alias)).build())
				.setNotification(Notification.newBuilder().setAlert(alert)
						.addPlatformNotification(IosNotification.newBuilder().addExtra("from", flag).build()).build())
				.build();
	}

	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.all())
				.setNotification(Notification.android(ALERT, TITLE, null)).build();
	}

	public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
		return PushPayload.newBuilder().setPlatform(Platform.ios()).setAudience(Audience.tag_and("tag1", "tag_all"))
				.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert(ALERT).setBadge(5)
								.setSound("happy").addExtra("from", "JPush").build())
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(true).build()).build();
	}

	public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.newBuilder().addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
						.addAudienceTarget(AudienceTarget.alias("alias1", "alias2")).build())
				.setMessage(Message.newBuilder().setMsgContent(MSG_CONTENT).addExtra("from", "JPush").build()).build();
	}
}
