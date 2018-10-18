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
		String dbUrl = "jdbc:mysql://10.10.0.12:3333/whxxfame";
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL).setUrl(dbUrl).setUsername("root").setPassword("root")
				.setDriverName("com.mysql.jdbc.Driver");
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setCapitalMode(true).setEntityLombokModel(false)
				.setNaming(NamingStrategy.underline_to_camel)
				// 修改替换成你需要的表名，多个表名传数组
				.setInclude(tableNames);
		config.setActiveRecord(false).setAuthor("huangxiang").setOutputDir("G:\\机场保洁项目").setFileOverride(true);
		if (!serviceNameStartWithI) {
			config.setServiceName("%sService");
		}
		new AutoGenerator().setGlobalConfig(config).setDataSource(dataSourceConfig).setStrategy(strategyConfig)
				.setPackageInfo(
						new PackageConfig().setParent(packageName).setController("controller").setEntity("entity"))
				.execute();
	}

	public static void main(String[] args) {
		String[] list = {"tb_equip","tb_equip_charge","tb_equip_maint","tb_equip_plan","tb_equip_repair","tb_equip_use","TB_EQUIP_PARTS"};//静态初始化的简化版
		generateByTables(true, "com.qcap.cac.entity", "tb_user_info");
	}

}
