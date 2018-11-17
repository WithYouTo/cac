package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dao.EquipMapper;
import com.qcap.cac.dao.EquipRepairMapper;
import com.qcap.cac.dto.EquipRepairSearchDto;
import com.qcap.cac.entity.TbEquipRepair;
import com.qcap.cac.service.EquipRepairSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.core.dao.TbManagerMapper;
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

    @Autowired
    private EquipMapper equipMapper;

    @Autowired
    private TbManagerMapper tbManagerMapper;

    @Override
    public void listEquipRepair(IPage<Map<String, Object>> page, EquipRepairSearchDto equipRepairSearchDto) {
        List<Map<String,Object>> list = this.equipRepairMapper.listEquipRepair(page,equipRepairSearchDto);
        page.setRecords(list);
    }

    @Override
    public void updateEquipRepair(String equipNo,String repairId, String userName,String operateCode) {
        TbEquipRepair repair = new TbEquipRepair();
        repair.setEquipRepairId(repairId);
        repair.setUpdateEmp(userName);
        repair.setStatus(operateCode);
        EntityTools.setUpdateEmpAndTime(repair);
        this.equipRepairMapper.updateEquipRepair(repair);
        this.equipMapper.updateEquipWorkStatusByEquipNoAndStatus(equipNo,operateCode,repair.getUpdateEmp());
    }
}
