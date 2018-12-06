package com.qcap.cac.dao;

import com.qcap.cac.entity.TbTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface GenDayTimeTaskJobMapper {
	
	Map<String, Object>selectShift(@Param("shift") String shift);
	
	List<Map<String, Object>>selectPlan(Map<String, Object> map);
	
	List<Map<String, Object>> selectPositionInfoByAreaCode(@Param("areaCode") String areaCode,@Param("shift") String shift);
	
	Map<String, Object> selectStandardInfo(@Param("standardCode") String standardCode);
	
	List<Map<String, Object>>selectWorkingEmployee(Map<String, Object> map);
	
	void insertTaskBatch(List<TbTask> list);
	
	String selectAreaName(@Param("areaCode") String areaCode);
	
	String selectDelayDays(@Param("planTimeType") String planTimeType);

	void updateJobRunTime(Map<String, Object> map);
	
}
