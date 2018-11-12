package com.qcap.cac.service;

import java.util.Map;

import javax.validation.Valid;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dto.BatchUpdateQrcodeDto;
import com.qcap.cac.dto.QueryQrcodeListDto;

public interface PrintQrcodeSrv {

	void queryQrcodeListByPage(IPage<Map<String, String>> page, QueryQrcodeListDto queryQrcodeListDto);

	void batchUpdateQrcode(@Valid BatchUpdateQrcodeDto batchUpdateQrcodeDto) throws Exception;

}
