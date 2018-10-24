package com.qcap.cac.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcap.cac.dto.GetAreaReq;
import com.qcap.cac.dto.GetAreaResp;
import com.qcap.cac.dto.GetPubCodeReq;
import com.qcap.cac.dto.GetPubCodeResp;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.core.entity.TbManager;

@Repository
public interface CommonRestMapper extends BaseMapper<TbManager> {

	public List<GetPubCodeResp> getPubCodeList(@Param("getPubCodeReq") GetPubCodeReq req);

	public List<GetAreaResp> getAreaList(@Param("getAreaReq") GetAreaReq req);

	public TbAreaPosition getPositionByPositionCode(String positionCode);

	public List<GetAreaResp> getAreaListByAreaCodeList(List<String> areaCodeList);

}
