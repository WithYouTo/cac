package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qcap.cac.dto.AppTaskArrangeShiftRestReq;
import com.qcap.cac.dto.AppTaskCheckTaskRestReq;
import com.qcap.cac.dto.AppTaskShiftHistoryRestReq;
import com.qcap.cac.dto.AppTaskUpdateReq;
import com.qcap.cac.entity.TbTask;
import com.qcap.cac.entity.TbTaskArrangeShift;
import com.qcap.cac.entity.TbTaskDelay;

public interface AppTaskRestMapper {
	
	String selectShiftType(Map<String, Object>  map);
	
	Map<String, Object> selectShiftTime (@Param("shift") String shift );
	
	List<Map<String, String>> queryTaskItem (Map<String, Object>  map);
	
	List<Map<String, Object>> selectTaskIntro (Map<String, Object>  map);
	
	Map<String, Object> queryTaskDetail (@Param("taskCode") String taskCode);
	
	void updateTask (AppTaskUpdateReq appTaskUpdateReq);
	
	List<Map<String, Object>> selectStandardDetailList (@Param("standardCode") String standardCode);
	
	Map<String, Object> selectStandardDetailInfo (@Param("standardDetailId") String standardDetailId);

	String selectAreaByPositionCode (@Param("positionCode") String positionCode);

	List<Map<String, String>> queryCheckTaskItem(Map<String, Object> map);

	List<Map<String, Object>> listTempTask (AppTaskShiftHistoryRestReq appTaskShiftHistoryRestReq);

	TbTask selectTaskByCode(@Param("taskCode") String taskCode);

	void insertArrangeShift(TbTaskArrangeShift arrangeShift);

	List<Map<String,Object>> selectArrangeShiftHistory (AppTaskShiftHistoryRestReq appTaskShiftHistoryRestReq);

	int updateCleanerTask (AppTaskArrangeShiftRestReq appTaskArrangeShiftRestReq);
	
	String selectIfTaskExist (AppTaskCheckTaskRestReq appTaskCheckTaskRestReq);
	
	List<String> selectIfCleanerHaveTasks (Map<String, Object> map);
	
	void insertTaskDelay(TbTaskDelay taskDelay);
	
	void updateSpecialTask(Map<String, Object> map);
	
	Map<String, String> selectTaskTime (@Param("taskCode") String taskCode);
	
	String selectSpecialTaskTimeType (@Param("taskCode") String taskCode);
	
	String selectEmployeeName (@Param("employeeCode") String employeeCode);
	
	String selectProgramCodeByEmployeeCode (@Param("employeeCode") String employeeCode,@Param("shift") String shift);
	
}
