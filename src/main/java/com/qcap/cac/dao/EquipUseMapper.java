package com.qcap.cac.dao;

import com.qcap.cac.entity.TbEquipUse;
import com.qcap.cac.dto.EquipUseSearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipUseMapper {

    List<Map<String, Object>> listEquipUse(EquipUseSearchParam equipUseSearchParam);

    String getUseTotalTimeByEquipId(String equipId);
}