package com.qcap.cac.service;

import com.qcap.cac.dto.EquipPlanSearchDto;

import java.util.List;
import java.util.Map;

public interface EquipPlanSrv {
    List<Map<String, Object>> listEquipPlan(EquipPlanSearchDto equipPlanSearchDto);

    List<Map<String, Object>> listPartsPlanByEquipId(String equipId);
}
