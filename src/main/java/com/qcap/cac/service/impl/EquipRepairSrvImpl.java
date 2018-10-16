package com.qcap.cac.service.impl;

import com.qcap.cac.dao.EquipRepairMapper;
import com.qcap.cac.dto.EquipRepairSearchDto;
import com.qcap.cac.service.EquipRepairSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipRepairSrvImpl implements EquipRepairSrv {

    @Autowired
    private EquipRepairMapper equipRepairMapper;


    @Override
    public List<Map<String,Object>> listEquipRepair(EquipRepairSearchDto equipRepairSearchDto) {
        return this.equipRepairMapper.listEquipRepair(equipRepairSearchDto);
    }

    @Override
    public void updateEquipRepair(String equipRepairId) {
        this.equipRepairMapper.updateEquipRepair(equipRepairId);
    }
}
