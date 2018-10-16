package com.qcap.cac.service;

import com.qcap.cac.dto.EquipUseSearchDto;

import java.util.List;
import java.util.Map;

public interface EquipUseSrv {
    List<Map<String, Object>> listEquipUse(EquipUseSearchDto equipUseSearchDto);

    String getUseTotalTimeByEquipId(String equipId);
}
