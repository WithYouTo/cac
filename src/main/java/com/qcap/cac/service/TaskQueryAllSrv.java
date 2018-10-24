package com.qcap.cac.service;

import com.qcap.cac.dto.TaskQueryAllSearchDto;

import java.util.List;
import java.util.Map;

public interface TaskQueryAllSrv {
	
	List<Map<String,Object>> selectAllTask(TaskQueryAllSearchDto taskQueryDto);
	
	List<Map<String,Object>> selectStandard();
	
	List<Map<String,Object>> selectPosition();
	
	List<Map<String,Object>> selectEmployee();
	
	Map<String,Object> selectStandardDetail(String standardCode);
}
