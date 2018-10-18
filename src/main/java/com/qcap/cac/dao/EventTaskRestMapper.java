package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.qcap.cac.dto.EventTaskRestDto;
import com.qcap.cac.entity.TbFlightInfo;

@Repository
public interface EventTaskRestMapper {
	
	void insertFlightInfo(TbFlightInfo flightInfo);
	
	List<Map<String,String>> selectEventPlan(EventTaskRestDto evenTaskDto );
	
	String selectAdvanceTime(@Param("eventBasicType") String eventBasicType);
}
