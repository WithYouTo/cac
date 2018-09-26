package com.qcap.cac.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qcap.cac.model.TbMsg;

@Repository
public interface CommonMapper {
	/**
	 * 根据管理单位查询楼宇
	 * @Title: selectBuildingByCode 
	 * @Description: TODO
	 * @param managementId
	 * @return
	 * @return: List<Map<String,Object>>
	 */
	List<Map<String, Object>>selectBuildingByCode(@Param("managementId")String managementId);
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
	Map<String, Object>selectLocation(@Param("name")String name,@Param("id")String id);
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
