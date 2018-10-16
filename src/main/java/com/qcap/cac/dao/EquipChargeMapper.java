package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.EquipChargeSearchDto;
import com.qcap.core.entity.TbManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipChargeMapper extends BaseMapper<TbManager> {
    List<Map<String, Object>> listEquipCharge(EquipChargeSearchDto equipChargeSearchDto);

    String getChargeTotalTimeByEquipId(String equipId);
}