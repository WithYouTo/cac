package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface GenNightTaskJobMapper {
	
	List<Map<String, Object>>selectNightPlan(Map<String, Object> map);

}
