package com.qcap.cac.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.AppTaskRestCheckReq;
import com.qcap.cac.dto.AppTaskRestReq;

public interface AppTaskRestSrv {
	
	Map<String, Object> queryTaskItem (String  employeeCode);
	
	List<Map<String, Object>> queryHistoryTask (AppTaskRestReq appTaskRestDto) throws IllegalAccessException, InvocationTargetException;
	
	List<Map<String, Object>> queryUnfinishTask(AppTaskRestReq appTaskRestDto);
	
	Map<String, Object> queryTaskDetail (String taskCode);
	
	List<Map<String, Object>> queryFinishAndCheckTask (AppTaskRestCheckReq appTaskRestCheckReq);
}
