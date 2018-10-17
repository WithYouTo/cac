package com.qcap.cac.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.TaskArrangeDto;
import com.qcap.cac.poiEntity.TaskArrangeUploadEntity;

public interface TaskArrangeSrv {
	
	List<Map<String, Object>> listTaskArrange (TaskArrangeDto taskArrangeDto);
	
	Object importTaskArrange(List<TaskArrangeUploadEntity> list,TaskArrangeDto taskArrangeDto, int row) throws IllegalAccessException, InvocationTargetException;

}
