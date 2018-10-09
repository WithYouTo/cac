package com.qcap.cac.dao;

import com.qcap.cac.entity.TbEquipCharge;
import com.qcap.cac.dto.EquipChargeSearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipChargeMapper {
    List<Map> listEquipCharge(EquipChargeSearchParam equipChargeSearchParam);

    String getChargeTotalTimeByEquipId(String equipId);
}