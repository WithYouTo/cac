package com.qcap.cac.service;

import com.qcap.cac.dto.EquipPlanSearchParam;

import java.util.List;
import java.util.Map;

public interface EquipPlanSrv {
    List<Map<String, Object>> listEquipPlan(EquipPlanSearchParam equipPlanSearchParam);

    List<Map<String, Object>> listPartsPlanByEquipId(String equipId);
}
