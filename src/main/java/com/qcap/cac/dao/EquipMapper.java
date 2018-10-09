package com.qcap.cac.dao;

import com.qcap.cac.entity.TbEquip;

public interface EquipMapper {
    int deleteByPrimaryKey(String equipId);

    int insert(TbEquip record);

    int insertSelective(TbEquip record);

    TbEquip selectByPrimaryKey(String equipId);

    int updateByPrimaryKeySelective(TbEquip record);

    int updateByPrimaryKeyWithBLOBs(TbEquip record);

    int updateByPrimaryKey(TbEquip record);
}