/**
 * 
 */
package com.qcap.core.configuration;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

/**
 * @author Administrator
 *
 */
@Configuration
public class DruidConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();

		boolean isExit = false;
		for (Filter filter : druidDataSource.getProxyFilters()) {
			if (filter instanceof WallFilter) {
				((WallFilter) filter).setConfig(wallConfig());
				isExit = true;
			}
		}
		if (!isExit) {
			List<Filter> filterList = new ArrayList<>();
			filterList.add(wallFilter());
			druidDataSource.setProxyFilters(filterList);
		}
		return druidDataSource;
	}

	@Bean
	public WallFilter wallFilter() {
		WallFilter wallFilter = new WallFilter();
		wallFilter.setConfig(wallConfig());
		return wallFilter;
	}

	@Bean
	public WallConfig wallConfig() {
		WallConfig wallConfig = new WallConfig();
		wallConfig.setMultiStatementAllow(true);// 允许一次执行多条语句
		wallConfig.setNoneBaseStatementAllow(true);// 允许一次执行多条语句
		return wallConfig;
	}
}
