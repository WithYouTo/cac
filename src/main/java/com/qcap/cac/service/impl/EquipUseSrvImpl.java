package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dao.EquipUseMapper;
import com.qcap.cac.dto.EquipUseSearchDto;
import com.qcap.cac.service.EquipUseSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipUseSrvImpl implements EquipUseSrv {

    @Autowired
    private EquipUseMapper equipUseMapper;

    @Override
    public void listEquipUse(IPage<Map<String, Object>> page, EquipUseSearchDto equipUseSearchDto) {
        List<Map<String,Object>> list = this.equipUseMapper.listEquipUse(page,equipUseSearchDto);
        page.setRecords(list);
    }

    @Override
    public String getUseTotalTimeByEquipId(String equipId) {
        DecimalFormat format = new DecimalFormat("0.00");
        String totalTime = this.equipUseMapper.getUseTotalTimeByEquipId(equipId);
        return format.format(new BigDecimal(totalTime));
    }
}
