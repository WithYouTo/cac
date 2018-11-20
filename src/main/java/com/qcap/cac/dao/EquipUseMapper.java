package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipUseSearchDto;
import com.qcap.cac.entity.TbEquipUse;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipUseMapper {

    List<Map<String, Object>> listEquipUse(IPage<Map<String, Object>> page, @Param("obj") EquipUseSearchDto equipUseSearchDto);

    String getUseTotalTimeByEquipId(String equipId);

    void insertEquipUse(TbEquipUse equipUse);

    TbEquipUse getEquipUseIdByEquipNoAndStatus(String equipNo);

    void updateEquipUseByEquipUseId(TbEquipUse eu);

    TbEquipUse getEquipUserByEquipNoAndStatus(String equipNo);
}