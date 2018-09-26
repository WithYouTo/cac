package com.qcap;

import com.qcap.core.properties.ConstantProperties;
import com.qcap.cac.common.listener.InitialConfigListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

/**
 * FaMe系统
 * @ClassName: WhFaMeApplication 
 * @Description: TODO
 * @author: 张天培(2017004)
 * @date: 2018年9月8日 下午12:33:33
 */
@MapperScan(basePackages = "com.qcap.**.dao")
@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties({ConstantProperties.class})
public class CacApplication {

	public static void main(String[] args) {
		SpringApplication springApplication=new SpringApplication(CacApplication.class);
		
		springApplication.addListeners(new InitialConfigListener());
		
		springApplication.run(args);
		
	}
	
}
