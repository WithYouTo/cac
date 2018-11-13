package com.qcap.cac.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.PrintQrcodeMapper;
import com.qcap.cac.dto.BatchUpdateQrcodeDto;
import com.qcap.cac.dto.QrcodeDto;
import com.qcap.cac.dto.QueryQrcodeListDto;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.PrintQrcodeSrv;
import com.qcap.cac.tools.RedisTools;

import cn.hutool.extra.qrcode.QrCodeUtil;

@Service
@Transactional
public class PrintQrcodeSrvImpl implements PrintQrcodeSrv {

	@Resource
	private PrintQrcodeMapper printQrcodeMapper;

	@Autowired
	private FastFileStorageClient storageClient;

	@Override
	public void queryQrcodeListByPage(IPage<Map<String, String>> page, QueryQrcodeListDto queryQrcodeListDto) {
		List<Map<String, String>> ls = printQrcodeMapper.selectQrcodeByPage(page, queryQrcodeListDto);
		page.setRecords(ls);
	}

	@Override
	public void batchUpdateQrcode(@Valid BatchUpdateQrcodeDto batchUpdateQrcodeDto) throws Exception {
		String content = RedisTools.getCommonConfig("CAC_QRCODE_CONTENT");
		String host = RedisTools.getCommonConfig("CAC_SERVICE_HOST");
		if (StringUtils.isBlank(content)) {
			throw new BaseException("CAC_QRCODE_CONTENT不能为空");
		}
		if (StringUtils.isBlank(host)) {
			throw new BaseException("CAC_SERVICE_HOST不能为空");
		}
		List<QrcodeDto> ls = batchUpdateQrcodeDto.getQrcodeList();
		if (CollectionUtils.isNotEmpty(ls)) {
			for (QrcodeDto qrcodeDto : ls) {
				content = RedisTools.getCommonConfig("CAC_QRCODE_CONTENT");
				if (CommonConstant.QRCODE_TYPE_EQUIP.equals(qrcodeDto.getQrcodeType())) {
					content += "?equipCode=" + qrcodeDto.getCode();
				} else if (CommonConstant.QRCODE_TYPE_POSITION.equals(qrcodeDto.getQrcodeType())) {
					content += "?positionCode=" + qrcodeDto.getCode();
				}
				String url = this.getQrCodeUrlByCode(content);
				qrcodeDto.setUrl(host + url);
			}
			printQrcodeMapper.batchUpdateQrcode(ls);
		}
	}

	private String getQrCodeUrlByCode(String code) throws Exception {
		String dir = RedisTools.getCommonConfig("CAC_AREA_SAVE_PATH");

		File directory = new File(dir);

		File file = QrCodeUtil.generate(code, 200, 200, directory);
		FileInputStream fileInputStream = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
				ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
		StorePath storePath = storageClient.uploadFile(multipartFile.getInputStream(), multipartFile.getSize(),
				FilenameUtils.getExtension(multipartFile.getOriginalFilename()), null);
		return storePath.getFullPath();
	}
}
