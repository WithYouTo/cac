package com.qcap.cac.dao;

import com.qcap.cac.entity.TbEquipRepair;
import org.springframework.stereotype.Repository;


@SuppressWarnings("rawtypes")
@Repository
public interface EquipRepairMapper {
    int deleteByPrimaryKey(String equipRepairId);

    int insert(TbEquipRepair record);

    int insertSelective(TbEquipRepair record);

    TbEquipRepair selectByPrimaryKey(String equipRepairId);

    int updateByPrimaryKeySelective(TbEquipRepair record);

    int updateByPrimaryKey(TbEquipRepair record);
}