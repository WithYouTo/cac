package com.qcap.cac.service;

import com.qcap.cac.dto.EquipChargeSearchParam;

import java.util.List;
import java.util.Map;

public interface EquipChargeSrv {
    List<Map> listEquipCharge(EquipChargeSearchParam equipChargeSearchParam);

    String getChargeTotalTimeByEquipId(String equipId);
}
