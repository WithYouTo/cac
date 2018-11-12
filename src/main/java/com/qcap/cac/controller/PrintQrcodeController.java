package com.qcap.cac.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dto.BatchUpdateQrcodeDto;
import com.qcap.cac.dto.QueryQrcodeListDto;
import com.qcap.cac.service.PrintQrcodeSrv;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.model.PageResParams;
import com.qcap.core.model.ResParams;

@RestController
@RequestMapping("/printQrcode")
public class PrintQrcodeController {

	@Resource
	private PrintQrcodeSrv printQrcodeSrv;

	@RequestMapping(value = "/queryQrcodeListByPage", method = RequestMethod.POST)
	public PageResParams queryQrcodeListByPage(IPage<Map<String, String>> page,
			@Valid QueryQrcodeListDto queryQrcodeListDto) {
		this.printQrcodeSrv.queryQrcodeListByPage(page, queryQrcodeListDto);
		return PageResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC,
				page.getTotal(), page.getRecords());
	}

	@RequestMapping(value = "/batchUpdateQrcode", method = RequestMethod.POST)
	public ResParams batchUpdateQrcode(@RequestBody @Valid BatchUpdateQrcodeDto batchUpdateQrcodeDto) throws Exception {
		this.printQrcodeSrv.batchUpdateQrcode(batchUpdateQrcodeDto);
		return ResParams.newInstance(CoreConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_PROCCESS_DESC);
	}

	@RequestMapping(value = "/selectQrcodeTypeList", method = RequestMethod.POST)
	public ResParams selectQrcodeTypeList() {
		Map<String, String> map = CommonConstant.QRCODE_TYPE;
		List<Map<String, String>> ls = new ArrayList<>();
		for (String key : map.keySet()) {
			Map<String, String> mapTmp = new HashMap<>();
			mapTmp.put("id", key);
			mapTmp.put("text", map.get(key));
			ls.add(mapTmp);
		}

		return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_QUERY_DESC, ls);
	}

}
