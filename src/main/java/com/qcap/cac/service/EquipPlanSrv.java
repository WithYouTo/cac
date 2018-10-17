package com.qcap.cac.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipPlanSearchDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EquipPlanSrv {
    void listEquipPlan(IPage<Map<String, Object>> page,EquipPlanSearchDto equipPlanSearchDto);

    void listPartsPlanByEquipId(IPage<Map<String, Object>> page,String equipId);
}
