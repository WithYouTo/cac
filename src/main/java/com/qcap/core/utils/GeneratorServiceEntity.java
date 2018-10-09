package com.qcap.core.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneratorServiceEntity {
	private static void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
		GlobalConfig config = new GlobalConfig();
		String dbUrl = "jdbc:mysql://localhost:3306/whxxerp";
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL).setUrl(dbUrl).setUsername("root").setPassword("root")
				.setDriverName("com.mysql.jdbc.Driver");
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setCapitalMode(true).setEntityLombokModel(false).setDbColumnUnderline(true)
				.setNaming(NamingStrategy.underline_to_camel)
				// 修改替换成你需要的表名，多个表名传数组
				.setInclude(tableNames);
		config.setActiveRecord(false).setAuthor("PH").setOutputDir("/Users/yandixuan/Desktop/").setFileOverride(true);
		if (!serviceNameStartWithI) {
			config.setServiceName("%sService");
		}
		new AutoGenerator().setGlobalConfig(config).setDataSource(dataSourceConfig).setStrategy(strategyConfig)
				.setPackageInfo(
						new PackageConfig().setParent(packageName).setController("controller").setEntity("entity"))
				.execute();
	}

	public static void main(String[] args) {
		generateByTables(true, "com.qcap.core", "tb_manager_role");
	}

}
