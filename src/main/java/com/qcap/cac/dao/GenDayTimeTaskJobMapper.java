package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface GenDayTimeTaskJobMapper {
	
	Map<String, Object>selectShift(@Param("shift")String shift);
	
	List<Map<String, Object>>selectPlan(Map<String, Object>map);
	
	Map<String, Object> selectPositionInfoByAreaCode(@Param("areaCode")String areaCode);
	
	
	
	
}
