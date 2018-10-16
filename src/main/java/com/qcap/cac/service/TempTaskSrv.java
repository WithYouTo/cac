package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.TempTaskDto;
import com.qcap.cac.dto.TempTaskSearchParam;
import com.qcap.core.model.ZTreeNode;

public interface TempTaskSrv {

	List<Map<String,Object>> listTask(TempTaskSearchParam paramDto);

	Map<String,Object> deleteTempTask(String taskCode);

	List<Map<String,Object>> selectStandardItem();

	List<ZTreeNode> selectAreaItem();

	Map<String, Object> insertTempTask(TempTaskDto taskDto);

	Map<String, Object> updateTempTask(TempTaskDto taskDto);
}
