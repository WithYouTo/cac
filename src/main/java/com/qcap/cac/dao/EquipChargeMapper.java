package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.entity.TbEquipCharge;
import com.qcap.cac.dto.EquipChargeSearchParam;
import com.qcap.core.entity.TbManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipChargeMapper extends BaseMapper<TbManager> {
    List<Map<String, Object>> listEquipCharge(@Param("equipChargeSearchParam") EquipChargeSearchParam equipChargeSearchParam);

    String getChargeTotalTimeByEquipId(String equipId);
}