package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dao.EquipChargeMapper;
import com.qcap.cac.dto.EquipChargeSearchDto;
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
    public void listEquipCharge(IPage<Map<String, Object>> page,EquipChargeSearchDto equipChargeSearchDto) {
        List<Map<String, Object>> list =   this.equipChargeMapper.listEquipCharge(page,equipChargeSearchDto);
        page.setRecords(list);
    }

    @Override
    public String getChargeTotalTimeByEquipId(String equipId) {
        return this.equipChargeMapper.getChargeTotalTimeByEquipId(equipId);
    }
}
