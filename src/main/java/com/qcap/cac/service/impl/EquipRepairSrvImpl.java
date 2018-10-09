package com.qcap.cac.service.impl;

import com.qcap.cac.dao.EquipRepairMapper;
import com.qcap.cac.service.EquipRepairSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EquipRepairSrvImpl implements EquipRepairSrv {

    @Autowired
    private EquipRepairMapper equipRepairMapper;


}
