package com.qcap.cac.service;

import com.qcap.cac.dto.EquipRepairSearchParam;

import java.util.List;
import java.util.Map;

public interface EquipRepairSrv {
    List<Map> listEquipRepair(EquipRepairSearchParam equipRepairSearchParam);

    void updateEquipRepair(String equipRepairId);
}
