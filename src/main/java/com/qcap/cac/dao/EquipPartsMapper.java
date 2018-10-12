package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.EquipMaintInsertParam;
import com.qcap.cac.entity.TbEquipParts;
import org.springframework.stereotype.Repository;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipPartsMapper extends BaseMapper<TbEquipParts>{
}
