package com.qcap.cac.service.impl;

import com.qcap.cac.dao.CommonMapper;
import com.qcap.cac.service.CommonSrv;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommonSrvImpl implements CommonSrv{

    @Resource
    private CommonMapper commonMapper;

    @Override
    public List<Map<String, String>> getEquipNameByEquipType(String equipType) {
        return this.commonMapper.getEquipNameByEquipType(equipType);
    }

    @Override
    public List<Map<String, String>> getPartsNameByEquipNo(String equipNo) {
        return this.commonMapper.getPartsNameByEquipNo(equipNo);
    }
}
