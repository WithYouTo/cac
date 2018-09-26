package com.qcap.cac.common.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.model.TbMsg;

public interface CommonSrv {
	/**
	 * 根据管理单位查询楼宇
	 * @Title: selectBuildingByCode 
	 * @Description: TODO
	 * @param managementId
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>>selectBuildingByCode(String managementId);
	/**
	 * 新增消息
	 * @Title: insertMsg 
	 * @Description: TODO
	 * @param msg
	 * @return: void
	 */
	void insertMsg(TbMsg msg);
	/**
	 * 查询省市区
	 * @Title: selectLocation 
	 * @Description: TODO
	 * @param name
	 * @param id
	 * @return
	 * @return: Map<String,Object>
	 */
	Map<String, Object>selectLocation(String name,String id);
	/**
	 * 根据当前登录人Id，查询其所在部门下的楼宇或公寓小区
	 * @Title: selectBuildingOrAreaByUserId 
	 * @Description: TODO
	 * @param map
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>>selectBuildingOrAreaByUserId(Map<String,Object>map);

}
