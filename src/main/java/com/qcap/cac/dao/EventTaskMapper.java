package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qcap.cac.entity.TbFlightInfo;

@Repository
public interface EventTaskMapper {
	
	void insertFlightInfo(TbFlightInfo flightInfo);
	
	List<Map<String,String>> selectEventPlan(Map<String,Object>map );
}
