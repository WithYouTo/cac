package com.qcap.cac.service.impl;

import com.qcap.cac.dao.EquipChargeMapper;
import com.qcap.cac.dao.EquipUseMapper;
import com.qcap.cac.dto.EquipUseSearchParam;
import com.qcap.cac.service.EquipUseSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipUseSrvImpl implements EquipUseSrv {

    @Autowired
    private EquipUseMapper equipUseMapper;

    @Override
    public List<Map<String, Object>> listEquipUse(EquipUseSearchParam equipUseSearchParam) {
        return this.equipUseMapper.listEquipUse(equipUseSearchParam);
    }

    @Override
    public String getUseTotalTimeByEquipId(String equipId) {
        return this.equipUseMapper.getUseTotalTimeByEquipId(equipId);
    }
}
