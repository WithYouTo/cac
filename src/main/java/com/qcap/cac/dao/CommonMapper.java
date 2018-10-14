package com.qcap.cac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.core.entity.TbManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Repository
public interface CommonMapper extends BaseMapper<TbManager> {

    List<Map<String,String>> getEquipNameByEquipType(String equipType);

    List<Map<String,String>> getPartsNameByEquipNo(String equipNo);
}
