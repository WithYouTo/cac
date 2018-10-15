package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.EquipSearchDto;
import com.qcap.cac.entity.TbEquip;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipMapper extends BaseMapper<TbEquip>{

    List<Map<String,Object>> listEquip(IPage<Map<String, Object>> page, @Param("")@Valid EquipSearchDto equipSearchDto);
}