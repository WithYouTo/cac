package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.core.entity.TbManager;

@Repository
public interface CommonMapper extends BaseMapper<TbManager> {

	List<Map<String, String>> getEquipNameByEquipType(String equipType);

	List<Map<String, String>> getPartsNameByEquipNo(String equipNo);

	List<Map<String, Object>> getAreaNameByAreaCode(String areaCode);

	List<Map<String, Object>> getStandardNameByStandardCode(String standardCode);

    List<Map<String,String>> getEquipTypeList();
}
