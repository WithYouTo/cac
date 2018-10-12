package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.TaskQueryAllSearchDto;

public interface TaskQueryAllSrv {
	
	List<Map>selectAllTask(TaskQueryAllSearchDto taskQueryDto);
	
	List<Map> selectStandard();
	
	List<Map> selectPosition();
	
//	List<Map> selectEmployee();
}
