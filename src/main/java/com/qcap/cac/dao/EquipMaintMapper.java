package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.entity.TbEquipMaint;
import org.springframework.stereotype.Repository;
import com.qcap.cac.dto.EquipMaintSearchDto;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface EquipMaintMapper extends BaseMapper<TbEquipMaint> {

    List<Map<String,Object>> listEquipMaint(EquipMaintSearchDto equipMaintSearchDto);
}