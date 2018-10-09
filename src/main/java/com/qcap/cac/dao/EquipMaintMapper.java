package com.qcap.cac.dao;

import com.qcap.cac.entity.TbEquipMaint;

public interface EquipMaintMapper {
    int deleteByPrimaryKey(String equipMaintId);

    int insert(TbEquipMaint record);

    int insertSelective(TbEquipMaint record);

    TbEquipMaint selectByPrimaryKey(String equipMaintId);

    int updateByPrimaryKeySelective(TbEquipMaint record);

    int updateByPrimaryKey(TbEquipMaint record);
}