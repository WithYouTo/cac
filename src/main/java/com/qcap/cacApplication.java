package com.qcap;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 物华信息demoCenter
 * 
 * @author 彭浩
 * @date 2018/6/4 15:11
 * @version 1.0
 */
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@SpringBootApplication
public class cacApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(cacApplication.class).bannerMode(Banner.Mode.OFF).run(args);

	}
}
