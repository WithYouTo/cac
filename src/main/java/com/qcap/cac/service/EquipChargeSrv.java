package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipChargeSearchParam;

import java.util.List;
import java.util.Map;

public interface EquipChargeSrv {
    List<Map<String, Object>> listEquipCharge(EquipChargeSearchParam equipChargeSearchParam);

    String getChargeTotalTimeByEquipId(String equipId);
}
