package com.qcap.cac.service;

import com.qcap.cac.dto.EquipChargeSearchDto;

import java.util.List;
import java.util.Map;

public interface EquipChargeSrv {
    List<Map<String, Object>> listEquipCharge(EquipChargeSearchDto equipChargeSearchDto);

    String getChargeTotalTimeByEquipId(String equipId);
}
