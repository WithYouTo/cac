package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipChargeSearchDto;

import java.util.List;
import java.util.Map;

public interface EquipChargeSrv {
    void listEquipCharge(IPage<Map<String, Object>> page, EquipChargeSearchDto equipChargeSearchDto);

    String getChargeTotalTimeByEquipId(String equipId);
}
