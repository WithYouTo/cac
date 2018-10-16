package com.qcap.cac.dao;

import com.qcap.cac.dto.EquipUseSearchDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipUseMapper {

    List<Map<String, Object>> listEquipUse(EquipUseSearchDto equipUseSearchDto);

    String getUseTotalTimeByEquipId(String equipId);
}