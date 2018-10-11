package com.qcap.cac.service.impl;

import com.qcap.cac.dao.EquipMaintMapper;
import com.qcap.cac.dto.EquipMaintSearchParam;
import com.qcap.cac.service.EquipMiantSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipMiantSrvImpl implements EquipMiantSrv {

    @Autowired
    private EquipMaintMapper equipMaintMapper;

    @Override
    public List<Map<String, Object>> listEquipMaint(EquipMaintSearchParam equipMaintSearchParam) {
        return this.equipMaintMapper.listEquipMaint(equipMaintSearchParam);
    }
}
