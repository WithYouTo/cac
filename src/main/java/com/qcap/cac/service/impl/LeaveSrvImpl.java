package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.LeaveMapper;
import com.qcap.cac.dto.LeaveSearchDto;
import com.qcap.cac.service.LeaveSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Transactional
public class LeaveSrvImpl implements LeaveSrv {

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public void listLeave(IPage<Map<String, Object>> page, LeaveSearchDto leaveSearchDto) {
        List<Map<String,Object>> list = this.leaveMapper.listLeave(page,leaveSearchDto);
        for (Map<String,Object> map : list){
            String leaveStatus = Objects.toString(map.get("leaveStatus"));
            map.put("leaveStatusName", CommonConstant.LEAVE_STATUS.get(leaveStatus));
        }
        page.setRecords(list);
    }
}
