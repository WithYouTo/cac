package com.qcap.cac.service;

import com.qcap.cac.dto.EquipUseSearchParam;

import java.util.List;
import java.util.Map;

public interface EquipUseSrv {
    List<Map<String, Object>> listEquipUse(EquipUseSearchParam equipUseSearchParam);

    String getUseTotalTimeByEquipId(String equipId);
}
