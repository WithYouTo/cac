package com.qcap.cac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcap.cac.dto.AppLeaveApplyReq;
import com.qcap.cac.dto.AppLeaveReq;
import com.qcap.cac.entity.TbLeave;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface LeaveRestSrv extends IService<TbLeave> {

    List<AppLeaveReq> queryLeaveList(Map<String,Object> paramMap);


    List<AppLeaveReq> queryAuditingList(Map<String,Object> paramMap);


    Integer insertLeaveApply(AppLeaveApplyReq appLeaveApplyReq,Map<String, MultipartFile> mapFile) throws Exception;


    AppLeaveApplyReq detailById(String leaveId);


    Integer cancelLeave(Map<String,Object> paramMap);


    Integer auditLeave(Map<String,Object> paramMap);


}
