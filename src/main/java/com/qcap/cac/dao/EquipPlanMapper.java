package com.qcap.cac.dao;

import com.qcap.cac.dto.EquipPlanSearchDto;
import com.qcap.cac.entity.TbEquipPlan;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EquipPlanMapper {
    List<Map<String, Object>> listEquipPlan(EquipPlanSearchDto equipPlanSearchDto);

    List<Map<String, Object>> listPartsPlanByEquipId(String equipId);

    void updateEquipPlan(TbEquipPlan equipPlan);
}
