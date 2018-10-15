package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dao.EquipMapper;
import com.qcap.cac.dao.EquipPartsMapper;
import com.qcap.cac.dto.EquipInsertDto;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.dto.PartsInsertDto;
import com.qcap.cac.entity.TbEquipParts;
import com.qcap.cac.service.EquipSrv;
import com.qcap.cac.tools.UUIDUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipSrvImpl implements EquipSrv {

    @Resource
    private EquipMapper equipMapper;

    @Resource
    private EquipPartsMapper equipPartsMapper;

    @Override
    public Map<String,String> insertParts(@Valid PartsInsertDto equipInsertParam) {
        String partsId = UUIDUtils.getUUID();
        TbEquipParts parts = new TbEquipParts();
        Map<String,String> map = new HashMap<String,String>();

        BeanUtils.copyProperties(equipInsertParam,parts);

        //判断equipId是否为空，若为空重新生成
        if(("").equals(equipInsertParam.getEquipId())){
            String equipId = UUIDUtils.getUUID();
            parts.setEquipId(equipId);
            map.put("equipId",equipId);
        }

        map.put("equipId",parts.getEquipId());
        parts.setPartsId(partsId);
        parts.setCreateDate(new Date());
        parts.setUpdateDate(new Date());
        parts.setCreateEmp("sys");
        parts.setUpdateEmp("sys");


        this.equipPartsMapper.insert(parts);

        return map;
    }

    @Override
    public void listPartsByEquipId(IPage<Map<String, Object>> page, String equipId) {
        List<Map<String, Object>> list = this.equipPartsMapper.listPartsByEquipId(page,equipId);
        page.setRecords(list);
    }

    @Override
    public Map<String, String> insertEquip(@Valid EquipInsertDto equipInsertDto) {
        return null;
    }

    @Override
    public void listEquip(IPage<Map<String, Object>> page, @Valid EquipSearchDto equipSearchDto) {
        List<Map<String, Object>> list = this.equipMapper.listEquip(page,equipSearchDto);
        page.setRecords(list);
    }
}
