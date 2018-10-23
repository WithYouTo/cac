package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipPlanSearchDto;
import com.qcap.cac.entity.TbEquipParts;
import com.qcap.cac.entity.TbEquipPlan;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EquipPlanMapper extends BaseMapper<TbEquipPlan>{
    List<Map<String, Object>> listEquipPlan(IPage<Map<String, Object>> page,@Param("map") EquipPlanSearchDto equipPlanSearchDto);

    List<Map<String, Object>> listPartsPlanByEquipId(IPage<Map<String, Object>> page,@Param("equipId") String equipId);

    void updateEquipPlan(TbEquipPlan equipPlan);

    TbEquipPlan selectPartsPlanByPartsId(String partsId);

    void updateNextMaintTime(TbEquipPlan parts);

    void deletePlanByPartsId(String partsId);

    void deletePlanByEquipId(@Param("equipId") String equipId);

    String selectPlanIdByEquipIdAndPartsId(TbEquipPlan equipPlan);

    String selectPlanIdByEquipId(TbEquipPlan equipPlan);
}
