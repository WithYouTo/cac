package com.qcap.cac.service.impl;

import com.qcap.cac.dao.LeaveMapper;
import com.qcap.cac.dto.LeaveSearchDto;
import com.qcap.cac.service.LeaveSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LeaveSrvImpl implements LeaveSrv {

    @Autowired
    private LeaveMapper leaveMapper;

    @Override
    public List<Map<String, Object>> listLeave(LeaveSearchDto leaveSearchDto) {
        return this.leaveMapper.listLeave(leaveSearchDto);
    }
}
