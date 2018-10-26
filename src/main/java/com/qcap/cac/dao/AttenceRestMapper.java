package com.qcap.cac.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.GetAttenceDetailsReq;
import com.qcap.cac.entity.TbAttence;
import com.qcap.core.entity.TbManager;

@Repository
public interface AttenceRestMapper extends BaseMapper<TbManager> {

	List<TbAttence> getAttenceList(GetAttenceDetailsReq req);

}
