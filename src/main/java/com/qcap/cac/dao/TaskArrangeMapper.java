package com.qcap.cac.dao;

import com.qcap.cac.dto.TaskArrangeDto;
import com.qcap.cac.dto.TaskArrangeSearchDto;
import com.qcap.cac.entity.TbTaskArrangement;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskArrangeMapper {
	
	List<Map<String, Object>> listTaskArrange (TaskArrangeSearchDto taskArrangeSearchDto);
	
	void insertTaskArrangeBatch (List<TbTaskArrangement> list );
	
	void deleteTaskArrange(TaskArrangeDto taskArrangeDto);

	List<Map<String,String>> selectPositionItem();

}
