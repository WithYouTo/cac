package com.qcap.cac.service;

import com.qcap.cac.dto.EquipRepairSearchDto;

import java.util.List;
import java.util.Map;

public interface EquipRepairSrv {
    List<Map<String, Object>> listEquipRepair(EquipRepairSearchDto equipRepairSearchDto);

    void updateEquipRepair(String equipRepairId);
}
