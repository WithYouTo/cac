package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qcap.cac.dto.TaskQueryAllSearchDto;
@Repository
public interface TaskQueryAllMapper {
	
	List<Map>selectAllTask(TaskQueryAllSearchDto taskQueryDto);
	
	List<Map> selectStandard();
	
	List<Map> selectPosition();
	
//	List<Map> selectEmployee();
	
}
