package com.qcap.cac.common.service;

import java.util.Map;

/** 
 * @ClassName: PaySettingQuerySrv 
 * @Description: TODO
 * @author: baojianxing
 * @date: 2018年5月30日 下午2:23:01  
 */
public interface PaySettingQuerySrv {

	
	/**
	 * 查询支付的基础参数
	 * @Title: selectPaySetting 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: Map
	 */
	public  Map selectPaySetting(Map param);
}
