package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.TempTaskDto;
import com.qcap.cac.dto.TempTaskSearchParam;

public interface TempTaskSrv {
	
	List<Map>listTask(TempTaskSearchParam paramDto);
	
	Map deleteTempTask(String taskCode);
	
	List<Map>selectStandardItem();
	
	List<Map>selectAreaItem();
	
	Map<String,Object> insertTempTask(TempTaskDto taskDto);
	
	Map<String,Object> updateTempTask(TempTaskDto taskDto);
}
