package com.qcap.cac.service.impl;

import com.qcap.cac.dao.EquipChargeMapper;
import com.qcap.cac.dto.EquipChargeSearchParam;
import com.qcap.cac.service.EquipChargeSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipChargeSrvImpl implements EquipChargeSrv {

    @Autowired
    private EquipChargeMapper equipChargeMapper;

    @Override
    public List<Map> listEquipCharge(EquipChargeSearchParam equipChargeSearchParam) {
        return this.equipChargeMapper.listEquipCharge(equipChargeSearchParam);
    }

    @Override
    public String getChargeTotalTimeByEquipId(String equipId) {
        return this.equipChargeMapper.getChargeTotalTimeByEquipId(equipId);
    }
}
