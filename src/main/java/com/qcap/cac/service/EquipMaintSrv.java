package com.qcap.cac.service;

import com.qcap.cac.dto.EquipMaintInsertParam;
import com.qcap.cac.dto.EquipMaintSearchParam;

import java.util.List;
import java.util.Map;

public interface EquipMaintSrv {
    List<Map<String,Object>> listEquipMaint(EquipMaintSearchParam equipMaintSearchParam);

    void insertEquipMaint(EquipMaintInsertParam equipMaintInsertParam);
}
