package com.qcap.cac.dao;

import com.qcap.cac.dto.TaskQueryAllSearchDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface TaskQueryAllMapper {
	
	List<Map<String,Object>> selectAllTask(TaskQueryAllSearchDto taskQueryDto);
	
	List<Map<String,Object>> selectStandard();
	
	List<Map<String,Object>> selectPosition();
	
	List<Map<String,Object>> selectEmployee();
	
	Map<String,Object> selectStandardInfo(@Param("standardCode")String standardCode);
	
	List<Map<String,Object>> selectFileInfo(@Param("groupId")String groupId);
	
}
