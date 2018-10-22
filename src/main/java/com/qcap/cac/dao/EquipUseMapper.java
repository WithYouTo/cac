package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipUseSearchDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipUseMapper {

    List<Map<String, Object>> listEquipUse(IPage<Map<String, Object>> page, @Param("obj") EquipUseSearchDto equipUseSearchDto);

    String getUseTotalTimeByEquipId(String equipId);
}