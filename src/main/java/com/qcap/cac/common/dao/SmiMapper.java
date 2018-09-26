package com.qcap.cac.common.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qcap.cac.model.Tbsmi01;

@Repository
public interface SmiMapper {
	
	/**
	 * 新增是否验证
	 * @Title: insert 
	 * @Description: TODO
	 * @param tbaccessLog
	 * @return: void
	 */
	void insertSmi(Tbsmi01 smi01);
	
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
