package com.qcap.cac.service.impl;

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
    public List<Map<String, Object>> listEquipPlan(EquipPlanSearchDto equipPlanSearchDto) {
        return this.equipPlanMapper.listEquipPlan(equipPlanSearchDto);
    }

    @Override
    public List<Map<String, Object>> listPartsPlanByEquipId(String equipId) {
        return this.equipPlanMapper.listPartsPlanByEquipId(equipId);
    }
}
