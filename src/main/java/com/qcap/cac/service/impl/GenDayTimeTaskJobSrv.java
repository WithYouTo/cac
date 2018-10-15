package com.qcap.cac.service.impl;

import org.springframework.beans.factory.annotation.Value;

public class GenDayTimeTaskJobSrv implements com.qcap.cac.service.GenDayTimeTaskJobSrv {
	
	@Value("${SHIFT_DAYTIME}")
	private String shift;

	@Override
	public void geneDayTimeTask() {
		/**
		 * 1、查询班次，获取开始时间和截止时间
		 * 2、查询所有周期性计划
		 * 3、查询岗位
		 * 4、查询值班人员
		 * 5、生成任务
		 */
		
		// 1、查询班次 
		
		

	}

}
