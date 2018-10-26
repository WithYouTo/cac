package com.qcap.cac.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.qcap.cac.dto.AttenceReq;
import com.qcap.cac.dto.GetAttenceDetailsReq;
import com.qcap.cac.dto.GetAttenceDetailsResp;
import com.qcap.cac.dto.GetAttenceReq;
import com.qcap.cac.dto.GetAttenceResp;

public interface AttenceRestSrv {

	void attence(AttenceReq req, List<MultipartFile> fileList) throws Exception;

	List<GetAttenceResp> getAttenceList(GetAttenceReq req) throws Exception;

	List<GetAttenceDetailsResp> getAttenceDetails(GetAttenceDetailsReq req) throws Exception;

}
