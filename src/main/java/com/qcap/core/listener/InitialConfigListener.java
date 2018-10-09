package com.qcap.core.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.qcap.core.service.ITbCommonConfigService;

import lombok.extern.slf4j.Slf4j;

/**
 * 项目启动成功后将配置文件添加到缓存
 * 
 * @author 帝都架构师聂银军
 * @date 2018年5月23日 上午9:35:49
 */
@Slf4j
public class InitialConfigListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent context) {
		ITbCommonConfigService service = context.getApplicationContext().getBean(ITbCommonConfigService.class);
		try {
			service.initialConfigCache();
			log.info("配置初始化完毕!");
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("配置初始化失败!");
		}
	}

}
