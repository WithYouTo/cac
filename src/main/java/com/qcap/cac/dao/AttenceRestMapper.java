package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.GetAttenceDetailsReq;
import com.qcap.core.entity.TbManager;

@Repository
public interface AttenceRestMapper extends BaseMapper<TbManager> {

	List<Map<String, String>> getAttenceList(GetAttenceDetailsReq req);

}
