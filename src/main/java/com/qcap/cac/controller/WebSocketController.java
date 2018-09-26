package com.qcap.cac.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qcap.core.utils.TenpayUtil;
import com.qcap.core.utils.UUIDUtil;
import com.qcap.cac.common.service.CommonSrv;
import com.qcap.cac.model.TbMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.qcap.core.config.SocketSessionRegistry;
import com.qcap.core.config.WebSocketConfig;

@RestController
public class WebSocketController {
	
	@Autowired
	private CommonSrv commonSrvImpl;
	
	@Autowired
	public SimpMessagingTemplate template;

	/** session操作类 */
	@Autowired
	public SocketSessionRegistry webAgentSessionRegistry;

	/*
	 * MessageMapping 跟requestMapping 语义一样 当前端发送地址为/app/helloWorld 匹配下面方法
	 * 
	 * @SendToUser 即为点对点通信 发送给订阅/user/queue/user的客户端
	 * 
	 * @SendTo 即为群发 发送给点阅到/topic/user的客户端
	 * 
	 * SimpMessagingTemplate.convertAndSendToUse等系列发送给不同订阅地址
	 */

	/**
	 * 发送消息到单个用户
	 * @Title: sendMessageToUser 
	 * @Description: TODO
	 * @param userId   用户ID
	 * @param account  登录人
	 * @param appMapping  客户端订阅地址
	 * @param message  消息
	 * @return: void
	 */
	public void sendMessageToUser(String userId, String account, String appMapping, String message) {
		TbMsg msg = new TbMsg();
		msg.setTbMsgId(UUIDUtil.getUUID());
		msg.setContent(message);
		msg.setReadFlag("0");
		msg.setUserId(userId);
		msg.setCreateDate(new Date());
		msg.setCreateEmp(account);
		msg.setVersion(0);
		commonSrvImpl.insertMsg(msg);
		String sessionId = webAgentSessionRegistry.getSessionIds(account).stream().findFirst().get();
		template.convertAndSendToUser(sessionId, appMapping, message, WebSocketConfig.createHeaders(sessionId));
	}

	public void sendMessageToMultipleUsers(List<Map<String, Object>> accountList, String appMapping, String message) {
		if (accountList != null && !accountList.isEmpty()) {
			for (Map<String, Object> map : accountList) {
				String account = TenpayUtil.toString(map.get("account"));
				String userId = TenpayUtil.toString(map.get("userId"));
				sendMessageToUser(userId, account, appMapping, message);
			}
		}
	}
	

}
