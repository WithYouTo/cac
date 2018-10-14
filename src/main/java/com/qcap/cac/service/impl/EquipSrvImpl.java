package com.qcap.cac.service.impl;

import com.qcap.cac.dao.EquipMapper;
import com.qcap.cac.service.EquipSrv;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class EquipSrvImpl implements EquipSrv {

    @Resource
    private EquipMapper equipMapper;
}
