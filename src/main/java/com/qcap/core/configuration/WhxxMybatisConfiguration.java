package com.qcap.core.configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisMapperRefresh;
import com.qcap.core.utils.AppUtils;

/**
 * @author yandixuan
 */

@Configuration
@MapperScan(basePackages = "com.qcap.**.dao")
public class WhxxMybatisConfiguration implements EnvironmentAware {
	private Environment environment;

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

	@Bean
	@ConditionalOnProperty(prefix = "mybatis", name = "mapperRefresh", havingValue = "true", matchIfMissing = true)
	public MybatisMapperRefresh mybatisMapperRefresh(SqlSessionFactory sqlSessionFactory) throws IOException {
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String locations = Objects.toString(environment.getProperty("mybatis-plus.mapper-locations"), "");
		Assert.notNull(locations, "mybatis-plus.mapper-locations:属性为空");
		Resource[] resources = pathMatchingResourcePatternResolver.getResources(locations);
		return new MybatisMapperRefresh(resources, sqlSessionFactory, 10, 5, true);
	}

	@Bean
	public WhxxMetaObjectHandler whxxMetaObjectHandler() {
		return new WhxxMetaObjectHandler();
	}

	@Bean
	public ConfigurationCustomizer mybatisConfigurationCustomizer() {
		return e -> e.setObjectWrapperFactory(new MybatisMapWrapperFactory());
	}

	/**
	 * 全局填充字段配置类
	 */
	private class WhxxMetaObjectHandler implements MetaObjectHandler {
		@Override
		public void insertFill(MetaObject metaObject) {
			setFieldValByName("createEmp", AppUtils.getLoginUserName(), metaObject);
			setFieldValByName("createTime", LocalDateTime.now(), metaObject);
			setFieldValByName("version", 0, metaObject);
		}

		@Override
		public void updateFill(MetaObject metaObject) {
			Object var1 = getFieldValByName("version", metaObject);
			int version = NumberUtils.toInt(Objects.toString(var1, ""));
			setFieldValByName("version", version + 1, metaObject);
			setFieldValByName("updateEmp", AppUtils.getLoginUserName(), metaObject);
			setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
		}
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
}
