package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.EquipMaintMapper;
import com.qcap.cac.dao.EquipMapper;
import com.qcap.cac.dao.EquipPartsMapper;
import com.qcap.cac.dto.EquipMaintInsertParam;
import com.qcap.cac.dto.EquipMaintSearchParam;
import com.qcap.cac.entity.TbEquip;
import com.qcap.cac.entity.TbEquipMaint;
import com.qcap.cac.entity.TbEquipParts;
import com.qcap.cac.service.EquipMaintSrv;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EquipMaintSrvImpl implements EquipMaintSrv {

    @Autowired
    private EquipMapper equipMapper;

    @Autowired
    private EquipMaintMapper equipMaintMapper;

    @Autowired
    private EquipPartsMapper equipPartsMapper;

    @Override
    public List<Map<String, Object>> listEquipMaint(EquipMaintSearchParam equipMaintSearchParam) {
        QueryWrapper<TbEquip> qw = new QueryWrapper();
        qw.eq("maint_Type",equipMaintSearchParam.getMaintType())
                .eq("equip_Name",equipMaintSearchParam.getEquipName()).eq("parts_Name",equipMaintSearchParam.getPartsName());
        return this.equipMaintMapper.listEquipMaint(equipMaintSearchParam);
    }

    @Override
    public void insertEquipMaint(EquipMaintInsertParam equipMaintInsertParam) {
        String maintType=equipMaintInsertParam.getMaintType();
        TbEquipMaint equipMaint = new TbEquipMaint();

        //mybatis-plus封装查询条件，根据设备编号获取设备信息
        QueryWrapper<TbEquip> equip = new QueryWrapper();
        equip.eq("equip_No",equipMaintInsertParam.getEquipNo());
        TbEquip equipInfo=this.equipMapper.selectOne(equip);

        BeanUtils.copyProperties(equipInfo,equipMaint);
        equipMaint.setEquipType(CommonConstant.MAINT_TYPE_EQUIP);
        //若维保类型是配件，继续查询配件信息
        if((CommonConstant.MAINT_TYPE_PARTS).equals(maintType)){
            //mybatis-plus封装查询条件，根据设备编号获取设备信息
            QueryWrapper<TbEquipParts> parts = new QueryWrapper();
            parts.eq("PARTS_NO",equipMaintInsertParam.getPartsNo());
            TbEquipParts equipParts = this.equipPartsMapper.selectOne(parts);
            BeanUtils.copyProperties(equipParts,equipMaint);
            equipMaint.setEquipType(CommonConstant.MAINT_TYPE_PARTS);
        }
        //1、重组维保记录对象
        //2、新增一条维保记录
        //3、更新维保计划
        this.equipMaintMapper.insert(equipMaint);
    }
}
