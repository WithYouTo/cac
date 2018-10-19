package com.qcap.cac.service;

import com.qcap.cac.dto.EquipMaintInsertDto;
import com.qcap.cac.dto.EquipMaintSearchDto;

import java.util.List;
import java.util.Map;

public interface EquipMaintSrv {
    List<Map<String,Object>> listEquipMaint(EquipMaintSearchDto equipMaintSearchDto);

    void insertEquipMaint(EquipMaintInsertDto equipMaintInsertDto) throws Exception;
}
