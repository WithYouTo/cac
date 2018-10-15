package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.entity.TbEquipParts;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipPartsMapper extends BaseMapper<TbEquipParts>{
    List<Map<String,Object>> listPartsByEquipId(IPage<Map<String, Object>> page, @Param("equipId") String equipId);
}
