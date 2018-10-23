package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dao.EquipPlanMapper;
import com.qcap.cac.dto.EquipPlanSearchDto;
import com.qcap.cac.service.EquipPlanSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipPlanSrvImpl implements EquipPlanSrv {

    @Autowired
    private EquipPlanMapper equipPlanMapper;

    @Override
    public void listEquipPlan(IPage<Map<String, Object>> page,EquipPlanSearchDto equipPlanSearchDto) {
        List<Map<String, Object>> list = this.equipPlanMapper.listEquipPlan(page,equipPlanSearchDto);
        page.setRecords(list);
    }


    @Override
    public void listPartsPlanByEquipId(IPage<Map<String, Object>> page,String equipId) {
        List<Map<String, Object>> list =  this.equipPlanMapper.listPartsPlanByEquipId(page,equipId);
        page.setRecords(list);
    }
}
