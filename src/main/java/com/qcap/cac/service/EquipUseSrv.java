package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipUseSearchDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EquipUseSrv {
    void listEquipUse(IPage<Map<String, Object>> page,@Param("obj")EquipUseSearchDto equipUseSearchDto);

    String getUseTotalTimeByEquipId(String equipId);
}
