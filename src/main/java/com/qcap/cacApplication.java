package com.qcap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

import com.github.tobato.fastdfs.FdfsClientConfig;

/**
 * 物华信息demoCenter
 * 
 * @author 彭浩
 * @date 2018/6/4 15:11
 * @version 1.0
 */
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableCaching
@SpringBootApplication
@MapperScan(basePackages = "com.qcap.**.dao")
public class cacApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(cacApplication.class).bannerMode(Banner.Mode.CONSOLE).run(args);
	}
}
