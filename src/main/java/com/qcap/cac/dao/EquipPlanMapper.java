package com.qcap.cac.dao;

import com.qcap.cac.dto.EquipPlanSearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EquipPlanMapper {
    List<Map<String, Object>> listEquipPlan(EquipPlanSearchParam equipPlanSearchParam);

    List<Map<String, Object>> listPartsPlanByEquipId(String equipId);
}
