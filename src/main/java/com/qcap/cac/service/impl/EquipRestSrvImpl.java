package com.qcap.cac.service.impl;

import com.qcap.cac.dao.EquipRestMapper;
import com.qcap.cac.dto.EquipListResp;
import com.qcap.cac.service.EquipRestSrv;
import com.qcap.core.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class EquipRestSrvImpl implements EquipRestSrv {

    @Resource
    private EquipRestMapper equipRestMapper;

    @Resource
    private TempTaskSrvImpl tempTaskSrvImpl;

    @Override
    public List<EquipListResp> getEquipList(String employeeCode) {
        List<EquipListResp> list = this.equipRestMapper.getEquipList(employeeCode);
        List<Map<String,Object>> tempList = this.tempTaskSrvImpl.selectCurrountWorkingEmployee(employeeCode);
        String shift = Objects.toString(tempList.get(0).get("shift"));
        Map<String,String> shiftTime = this.equipRestMapper.getShiftTimeByShift(shift);
        for(EquipListResp ep : list){

            //todo 重组对象，加入设备使用时间
        }
        return null;
    }


}
