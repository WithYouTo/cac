package com.qcap.cac.service;

import java.util.List;
import java.util.Map;

import com.qcap.cac.dto.*;
import org.springframework.web.multipart.MultipartFile;

public interface AttenceRestSrv {

	void attence(AttenceReq req, List<MultipartFile> fileList) throws Exception;

	List<GetAttenceResp> getAttenceList(GetAttenceReq req) throws Exception;

	List<GetAttenceDetailsResp> getAttenceDetails(GetAttenceDetailsReq req) throws Exception;

	List<Map<String ,String>> getEmpArrangeShift(GetEmpArrangeShiftDto getTaskArrangementDto) throws Exception;
}
