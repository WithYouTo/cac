package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipRepairSearchDto;

import java.util.Map;

public interface EquipRepairSrv {
    void listEquipRepair(IPage<Map<String, Object>> page, EquipRepairSearchDto equipRepairSearchDto);

    void updateEquipRepair(String equipNo,String repairId, String equipRepairId,String operateCode);
}
