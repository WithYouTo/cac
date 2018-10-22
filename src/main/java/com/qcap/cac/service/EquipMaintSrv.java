package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipMaintInsertDto;
import com.qcap.cac.dto.EquipMaintSearchDto;

import java.util.List;
import java.util.Map;

public interface EquipMaintSrv {
    void listEquipMaint(IPage<Map<String, Object>> page, EquipMaintSearchDto equipMaintSearchDto);

    void insertEquipMaint(EquipMaintInsertDto equipMaintInsertDto);
}
