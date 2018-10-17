package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qcap.cac.dto.TaskArrangeDto;
import com.qcap.cac.entity.TbTaskArrangement;

@Repository
public interface TaskArrangeMapper {
	
	List<Map<String, Object>> listTaskArrange (TaskArrangeDto taskArrangeDto);
	
	void insertTaskArrangeBatch (List<TbTaskArrangement> list );
	
	void deleteTaskArrange(TaskArrangeDto taskArrangeDto);

}
