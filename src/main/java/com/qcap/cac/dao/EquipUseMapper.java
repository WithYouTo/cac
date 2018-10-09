package com.qcap.cac.dao;

import com.qcap.cac.entity.TbEquipUse;
import com.qcap.cac.dto.EquipUseSearchParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipUseMapper {
    int deleteByPrimaryKey(String equipUseId);

    int insert(TbEquipUse record);

    int insertSelective(TbEquipUse record);

    TbEquipUse selectByPrimaryKey(String equipUseId);

    int updateByPrimaryKeySelective(TbEquipUse record);

    int updateByPrimaryKey(TbEquipUse record);

    List<Map> listEquipUse(EquipUseSearchParam equipUseSearchParam);

    String getUseTotalTimeByEquipId(String equipId);
}