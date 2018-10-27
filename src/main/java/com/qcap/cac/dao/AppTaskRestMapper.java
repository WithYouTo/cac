package com.qcap.cac.dao;

import com.qcap.cac.dto.AppTaskUpdateReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppTaskRestMapper {
	
	String selectshiftType (Map<String, Object>  map);
	
	Map<String, Object> selectShiftTime (@Param("shift") String shift );
	
	List<Map<String, String>> queryTaskItem (Map<String, Object>  map);
	
	List<Map<String, Object>> selectTaskIntro (Map<String, Object>  map);
	
	Map<String, Object> queryTaskDetail (@Param("taskCode") String taskCode);
	
	void updateTask (AppTaskUpdateReq appTaskUpdateReq);
	
	List<Map<String, Object>> selectStandardDetailList (@Param("standardCode") String standardCode);
	
	Map<String, Object> selectStandardDetailInfo (@Param("standardDetailId") String standardDetailId);

	String selectAreaByPositionCode (@Param("positionCode") String positionCode);

	List<Map<String, String>> queryCheckTaskItem(Map<String, Object> map);
	
}
