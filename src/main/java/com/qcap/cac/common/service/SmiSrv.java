package com.qcap.cac.common.service;

import java.util.Map;

import com.qcap.cac.model.Tbsmi01;
/**
 *  验证码登录
 * @ClassName: AccessLogSrv 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年3月27日 下午6:49:08
 */
public interface SmiSrv {

	/**
	 * 新增是否验证
	 * @Title: insert 
	 * @Description: TODO
	 * @param tbaccessLog
	 * @return: void
	 */
	void insert(Tbsmi01 smi01);
	
	/**
	 * 验证短信
	 * @Title: selectCode 
	 * @Description: TODO
	 * @param param
	 * @return
	 * @return: Map
	 */
	Map selectCode(Map param);
}
