package com.qcap.cac.dao;

import com.qcap.cac.dto.EquipRepairSearchParam;
import com.qcap.cac.entity.TbEquipRepair;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@SuppressWarnings("rawtypes")
@Repository
public interface EquipRepairMapper {
    int insert(TbEquipRepair record);

    List<Map<String,Object>> listEquipRepair(EquipRepairSearchParam equipRepairSearchParam);

    void updateEquipRepair(String equipRepairId);
}