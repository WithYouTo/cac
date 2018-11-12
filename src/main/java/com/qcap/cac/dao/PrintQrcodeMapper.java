package com.qcap.cac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.QrcodeDto;
import com.qcap.cac.dto.QueryQrcodeListDto;
import com.qcap.cac.entity.TbPlan;

@Repository
public interface PrintQrcodeMapper extends BaseMapper<TbPlan> {

	List<Map<String, String>> selectQrcodeByPage(IPage<Map<String, String>> page,
			@Param("param") QueryQrcodeListDto queryQrcodeListDto);

	void batchUpdateQrcode(List<QrcodeDto> ls);
}