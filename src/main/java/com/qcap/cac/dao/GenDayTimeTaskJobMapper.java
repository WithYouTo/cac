package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qcap.cac.entity.TbTask;
@Repository
public interface GenDayTimeTaskJobMapper {
	
	Map<String, Object>selectShift(@Param("shift") String shift);
	
	List<Map<String, Object>>selectPlan(Map<String, Object> map);
	
	Map<String, Object> selectPositionInfoByAreaCode(@Param("areaCode") String areaCode);
	
	Map<String, Object> selectStandardInfo(@Param("standardCode") String standardCode);
	
	List<Map<String, Object>>selectWorkingEmployee(Map<String, Object> map);
	
	void insertTaskBatch(List<TbTask> list);
	
	String selectAreaName(@Param("areaCode") String areaCode);
	
	String selectDelayDays(@Param("planTimeType") String planTimeType);
	
}
