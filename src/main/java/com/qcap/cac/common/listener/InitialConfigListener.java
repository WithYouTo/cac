package com.qcap.cac.common.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.qcap.core.service.CommonConfigSrv;

/**
 * 项目启动成功后将配置文件添加到缓存
 * @ClassName: InitialConfigListener 
 * @Description: TODO
 * @author: 聂**
 * @date: 2018年5月23日 上午9:35:49
 */
public class InitialConfigListener implements ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger log = LoggerFactory.getLogger(InitialConfigListener.class);
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent context) {
		CommonConfigSrv service = context.getApplicationContext().getBean(CommonConfigSrv.class);
		try {
			service.inititalConfigCache();
			log.info("配置初始化完毕!");
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("配置初始化失败!");
		}
	}

}
