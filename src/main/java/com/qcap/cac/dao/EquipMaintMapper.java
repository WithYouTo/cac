package com.qcap.cac.dao;

import com.qcap.cac.entity.TbEquipMaint;
import org.springframework.stereotype.Repository;
import com.qcap.cac.dto.EquipMaintSearchParam;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipMaintMapper {
    int deleteByPrimaryKey(String equipMaintId);

    int insert(TbEquipMaint record);

    int insertSelective(TbEquipMaint record);

    TbEquipMaint selectByPrimaryKey(String equipMaintId);

    int updateByPrimaryKeySelective(TbEquipMaint record);

    int updateByPrimaryKey(TbEquipMaint record);

    List<Map<String,Object>> listEquipMaint(EquipMaintSearchParam equipMaintSearchParam);
}