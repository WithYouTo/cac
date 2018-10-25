package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.EquipChargeMapper;
import com.qcap.cac.dao.EquipMapper;
import com.qcap.cac.dao.EquipRestMapper;
import com.qcap.cac.dao.EquipUseMapper;
import com.qcap.cac.dto.EquipListResp;
import com.qcap.cac.dto.UpdateEquipStatusReq;
import com.qcap.cac.dto.UpdateStopEquipStatusReq;
import com.qcap.cac.dto.UpdateUsingEquipStatusReq;
import com.qcap.cac.entity.TbEquip;
import com.qcap.cac.entity.TbEquipCharge;
import com.qcap.cac.entity.TbEquipUse;
import com.qcap.cac.service.EquipRestSrv;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.common.CoreConstant;
import com.qcap.core.dao.TbManagerMapper;
import com.qcap.core.entity.TbManager;
import com.qcap.core.model.ResParams;
import com.qcap.core.utils.DateUtil;
import org.springframework.beans.BeanUtils;
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
    private EquipMapper equipMapper;

    @Resource
    private EquipUseMapper equipUseMapper;

    @Resource
    private EquipChargeMapper equipChargeMapper;

    @Resource
    private TempTaskSrvImpl tempTaskSrvImpl;

    @Resource
    private TbManagerMapper tbManagerMapper;

    @Override
    public List<EquipListResp> getEquipList(String employeeCode) {
        List<EquipListResp> list = this.equipRestMapper.getEquipList(employeeCode);
        return rebuildEquipListWithUseTime(list,employeeCode);
    }

    @Override
    public List<EquipListResp> getUnrevertEquipList(String employeeCode) {
        List<EquipListResp> list = this.equipRestMapper.getUnrevertEquipList(employeeCode);
        return rebuildEquipListWithUseTime(list,employeeCode);
    }

    @Override
    public Map<String, Object> getEquipStatus(String equipNo) {
        Map<String,Object> map = this.equipRestMapper.getEquipStatus(equipNo);
        String status = Objects.toString(map.get("status"));
        map.put("statusName", CommonConstant.EQUIP_WORK_STATUS.get(status));
        return map;
    }

    @Override
    public void updateEquipStatus(UpdateEquipStatusReq updateEquipStatusReq) {
        String status = updateEquipStatusReq.getStatus();
        String equipNo = updateEquipStatusReq.getEquipNo();

        Map<String,Object> map = this.equipRestMapper.getEquipStatus(equipNo);
        String curStatus = Objects.toString(map.get("status"));

        if(("INSTOP").equals(curStatus)){

        }


    }

    @Override
    public ResParams updateStopEquipStatus(UpdateStopEquipStatusReq updateStopEquipStatusReq) {
        String employeeCode = updateStopEquipStatusReq.getEmployeeCode();
        String equipNo = updateStopEquipStatusReq.getEquipNo();
        //获取当前设备状态
        Map<String,Object> map = this.equipRestMapper.getEquipStatus(equipNo);
        String curStatus = Objects.toString(map.get("status"));
        //根据员工编号获取员工信息
        TbManager manager = this.tbManagerMapper.getMangerByEmployeeCode(employeeCode);
        TbEquipUse equipUse = new TbEquipUse();
        //当设备正在维修中，返回提示
        if(CommonConstant.EQUIP_WORK_STATUS_INREPAIR.equals(curStatus)){
            return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", null);
        }
        //当设备正在充电中，先更新设备充电记录，再插入设备使用记录
        if(CommonConstant.EQUIP_WORK_STATUS_INCHARGE.equals(curStatus)){
            handlerWhenEquipInCharge(manager,equipNo);
        }
        TbEquip equip =this.equipMapper.selectEquipByEquipNo(equipNo);
        BeanUtils.copyProperties(equip,equipUse);
        //重组equipUse对象
        equipUse.setEquipUseId(UUIDUtils.getUUID());
        equipUse.setStatus(CommonConstant.EQUIP_USE_STATUS_INUSE);
        equipUse.setCreateEmp(employeeCode);
        equipUse.setUpdateEmp(employeeCode);
        equipUse.setPersonMobile(manager.getPhone());
        equipUse.setPersonNo(manager.getAccount());
        equipUse.setPersonName(manager.getName());
        //新增设备使用记录
        this.equipUseMapper.insertEquipUse(equipUse);

        //通过设备编号将设备信息表中的设备工作状态改为使用中
        this.equipMapper.updateStopEquipStatus(equipNo);
        return ResParams.newInstance(CoreConstant.SUCCESS_CODE, "", null);
    }

    @Override
    public void updateUsingEquipStatus(UpdateUsingEquipStatusReq updateUsingEquipStatusReq) {
        String employeeCode = updateUsingEquipStatusReq.getEmployeeCode();
        String equipNo = updateUsingEquipStatusReq.getEquipNo();
        String operateCode = updateUsingEquipStatusReq.getOperateCode();
        //通过工号，获取人员信息
        TbManager manager = this.tbManagerMapper.getMangerByEmployeeCode(employeeCode);

        //更新设备使用记录表中的数据
        //通过设备编码和使用状态查询设备使用记录
        TbEquipUse equipUse= this.equipUseMapper.getEquipUseIdByEquipNoAndStatus(equipNo);

        Date endUseTime = new Date();
        //重组设备充电记录对象
        TbEquipUse eu = new TbEquipUse();
        eu.setEquipUseId(equipUse.getEquipUseId());
        eu.setUpdateEmp(manager.getAccount());
        eu.setEndUseTime(endUseTime);
        eu.setUpdateDate(endUseTime);
        eu.setStatus(CommonConstant.EQUIP_USE_STATUS_USED);
        eu.setTotalUseTime(getTotalTime(equipUse.getStartUseTime(),endUseTime));
        //更新设备充电记录中结束充电时间和充电时长
        this.equipUseMapper.updateEquipUseByEquipUseId(eu);


        //获取当前设备的工作状态
        Map<String,Object> map = this.equipRestMapper.getEquipStatus(equipNo);
        String curStatus = Objects.toString(map.get("status"));

        if(CommonConstant.EQUIP_WORK_STATUS_INREPAIR.equals(operateCode)){

        }
        if(CommonConstant.EQUIP_WORK_STATUS_INCHARGE.equals(operateCode)){

        }
        if(CommonConstant.EQUIP_WORK_STATUS_INSTOP.equals(operateCode)){

        }
    }

    /**
     *
     * @Description: 将时间加入equipListResp
     *
     *
     * @MethodName: rebuildEquipListWithUseTime
     * @Parameters: [list, employeeCode]
     * @ReturnType: java.util.List<com.qcap.cac.dto.EquipListResp>
     *
     * @author huangxiang
     * @date 2018/10/25 16:55
     */
    private List<EquipListResp> rebuildEquipListWithUseTime(List<EquipListResp> list, String employeeCode){
        List<Map<String,Object>> tempList = this.tempTaskSrvImpl.selectCurrountWorkingEmployee(employeeCode);
        String shift = Objects.toString(tempList.get(0).get("shift"));
        Map<String,String> shiftTime = this.equipRestMapper.getShiftTimeByShift(shift);
        String startTime = shiftTime.get("startTime");
        String endTime = shiftTime.get("endTime");
        StringBuilder sb = new StringBuilder("");
        for(EquipListResp ep : list){
            ep.setUseTime(sb.append(startTime).append("-").append(endTime).toString());
        }
        return list;
    }


    /**
     *
     * @Description: 当设备正在充电中，先更新设备充电记录，再插入设备使用记录
     *
     *
     * @MethodName: handlerWhenEquipInCharge
     * @Parameters: [manager, equipNo]
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/25 16:54
     */
    private void handlerWhenEquipInCharge(TbManager manager,String equipNo) {
        //通过设备编码和充电状态查询设备充电记录
        TbEquipCharge equipCharge= this.equipChargeMapper.getEquipChargeIdByEquipNoAndStatus(equipNo);
        Date endChargeTime = new Date();
        //重组设备充电记录对象
        TbEquipCharge ec = new TbEquipCharge();
        ec.setEquipChargeId(equipCharge.getEquipChargeId());
        ec.setUpdateEmp(manager.getAccount());
        ec.setEndChargeTime(endChargeTime);
        ec.setUpdateDate(endChargeTime);
        ec.setStatus(CommonConstant.EQUIP_CHARGE_STATUS_CHARGED);
        ec.setTotalChargeTime(getTotalTime(equipCharge.getStartChargeTime(),endChargeTime));
        //更新设备充电记录中结束充电时间和充电时长
        this.equipChargeMapper.updateEquipChargeByEquipChargeId(ec);
    }

    /**
     *
     * @Description: 获取两个时间之间的小时数
     *
     *
     * @MethodName: getTotalTime
     * @Parameters: [startChargeTime, endChargeTime]
     * @ReturnType: java.lang.String
     *
     * @author huangxiang
     * @date 2018/10/25 16:54
     */
    private String getTotalTime(Date startChargeTime, Date endChargeTime) {
        long nh = 1000 * 60 * 60;
        long diff = endChargeTime.getTime() - startChargeTime.getTime();
        // 计算差多少小时
        double hour = diff/(double)nh;
        return String.format("%.2f", hour);
    }

}
