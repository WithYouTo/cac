package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.qcap.cac.dto.AppTaskUpdateDto;

public interface AppTaskRestMapper {
	
	String selectshiftType (Map<String, Object>  map);
	
	Map<String, Object> selectShiftTime (@Param("shift") String shift );
	
	List<Map<String, String>> queryTaskItem (Map<String, Object>  map);
	
	List<Map<String, Object>> selectTaskIntro (Map<String, Object>  map);
	
	Map<String, Object> queryTaskDetail (@Param("taskCode") String taskCode);
	
	List<Map<String, Object>> selectSysFile (@Param("groupId") String groupId);
	
	void updateTask (AppTaskUpdateDto appTaskUpdateDto);
	
	List<Map<String, Object>> selectStandardDetailList (@Param("standardCode") String standardCode);
	
	Map<String, Object> selectStandardDetailInfo (@Param("standardDetailId") String standardDetailId);
	
}
