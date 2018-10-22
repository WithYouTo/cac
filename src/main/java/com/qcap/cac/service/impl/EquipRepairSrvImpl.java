package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dao.EquipRepairMapper;
import com.qcap.cac.dto.EquipRepairSearchDto;
import com.qcap.cac.entity.TbEquipRepair;
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
    public void listEquipRepair(IPage<Map<String, Object>> page, EquipRepairSearchDto equipRepairSearchDto) {
        List<Map<String,Object>> list = this.equipRepairMapper.listEquipRepair(page,equipRepairSearchDto);
        page.setRecords(list);
    }

    @Override
    public void updateEquipRepair(String repairId, String userName) {
        TbEquipRepair repair = new TbEquipRepair();
        repair.setEquipRepairId(repairId);
        repair.setUpdateEmp(userName);
        this.equipRepairMapper.updateEquipRepair(repair);
    }
}
