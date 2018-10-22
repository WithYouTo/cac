package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipRepairSearchDto;
import com.qcap.cac.entity.TbEquipRepair;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@SuppressWarnings("rawtypes")
@Repository
public interface EquipRepairMapper {
    int insert(TbEquipRepair record);

    List<Map<String,Object>> listEquipRepair(IPage<Map<String, Object>> page,@Param("obj") EquipRepairSearchDto equipRepairSearchDto);

    void updateEquipRepair(TbEquipRepair repair);
}