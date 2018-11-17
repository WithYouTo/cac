package com.qcap.cac.service;

import com.qcap.cac.dto.TaskArrangeDto;
import com.qcap.cac.dto.TaskArrangeSearchDto;
import com.qcap.cac.poiEntity.TaskArrangeUploadEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public interface TaskArrangeSrv {
	
	List<Map<String, Object>> listTaskArrange (TaskArrangeSearchDto taskArrangeSearchDto);
	
	Object importTaskArrange(List<TaskArrangeUploadEntity> list,TaskArrangeDto taskArrangeDto, int row) throws IllegalAccessException, InvocationTargetException;

	List<Map<String,String>> selectPositionItem(String programCode);
}
