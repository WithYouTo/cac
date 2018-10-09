/**   
 * Copyright © 2018 武汉现代物华科技发展有限公司信息分公司. All rights reserved.
 * 
 * @Title: ActiveMqUtil.java 
 * @Prject: Whhotel
 * @Package: com.whxx.hotel.mq
 * @author: 彭浩
 * @date: 2018年3月29日 上午11:42:05 
 * @version: V1.0   
 */
package com.qcap.core.utils;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * ActiveMq工具类 ，提供发送和接收
 * 
 * @ClassName: ActiveMqUtil
 * @author: 彭浩
 * @date: 2018年3月29日 上午11:42:05
 */
@Component
public class ActiveMqUtil {

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	public void sendMessage(String queueName, Object payload) {

		jmsTemplate.convertAndSend(new ActiveMQQueue(queueName), payload);

	}

}
