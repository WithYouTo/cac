package com.qcap.cac.service.impl;

import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.*;
import com.qcap.cac.dto.*;
import com.qcap.cac.entity.TbEquip;
import com.qcap.cac.entity.TbEquipCharge;
import com.qcap.cac.entity.TbEquipRepair;
import com.qcap.cac.entity.TbEquipUse;
import com.qcap.cac.service.EquipRestSrv;
import com.qcap.cac.tools.JpushTools;
import com.qcap.cac.tools.RedisTools;
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
    private EquipRepairMapper equipRepairMapper;

    @Resource
    private TempTaskSrvImpl tempTaskSrvImpl;

    @Resource
    private TbManagerMapper tbManagerMapper;

    @Override
    public List<EquipListResp> getEquipList(EquipListReq equipListReq) {
        List<EquipListResp> list = this.equipRestMapper.getEquipTypeList(equipListReq);
        return rebuildEquipListWithUseTime(list,equipListReq.getEmployeeCode());
    }

    @Override
    public List<ListUnrevertEquipResp> getUnrevertEquipList(String employeeCode) {
        List<ListUnrevertEquipResp> list = this.equipRestMapper.getUnrevertEquipList(employeeCode);
        return rebuildUnrevertEquipListWithUseTime(list,employeeCode);
    }

    @Override
    public GetEquipStatusResp getEquipStatus(String equipNo) {
        String url = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
        GetEquipStatusResp esr = this.equipRestMapper.getEquipStatus(equipNo);
        String status = Objects.toString(esr.getStatus());
        esr.setStatusName(CommonConstant.EQUIP_WORK_STATUS.get(status));
        esr.setUrl(url+esr.getUrl());
        return esr;
    }

    @Override
    public ResParams updateStopEquipStatus(UpdateStopEquipStatusReq updateStopEquipStatusReq) {
        String employeeCode = updateStopEquipStatusReq.getEmployeeCode();
        String equipNo = updateStopEquipStatusReq.getEquipNo();
        //获取当前设备状态
        TbEquip equip =this.equipMapper.selectEquipByEquipNo(equipNo);
        String curStatus = equip.getEquipWorkState();
//        GetEquipStatusResp esr = this.equipRestMapper.getEquipStatus(equipNo);
//        String curStatus = Objects.toString(esr.getStatus());
        //根据员工编号获取员工信息
        TbManager manager = this.tbManagerMapper.getMangerByEmployeeCode(employeeCode);
        //当设备正在充电中
        if(CommonConstant.EQUIP_WORK_STATUS_INCHARGE.equals(curStatus)){
            //调用设备状态为充电中的方法
            handlerWhenEquipInCharge(manager,equipNo);
        }
        handlerWhenOperateCodeIsInUse(equip,manager,employeeCode);

        return ResParams.newInstance(CommonCodeConstant.SUCCESS_CODE, CommonCodeConstant.SUCCESS_PROCCESS_DESC, null);
    }

    @Override
    public void updateUsingEquipStatus(UpdateUsingEquipStatusReq updateUsingEquipStatusReq) {
        String employeeCode = updateUsingEquipStatusReq.getEmployeeCode();
        String equipNo = updateUsingEquipStatusReq.getEquipNo();
        String operateCode = updateUsingEquipStatusReq.getOperateCode();
        //获取当前设备状态
        TbEquip equip =this.equipMapper.selectEquipByEquipNo(equipNo);
        String curStatus = equip.getEquipWorkState();
//        GetEquipStatusResp esr = this.equipRestMapper.getEquipStatus(equipNo);
//        String curStatus = esr.getStatus();
        //通过工号，获取人员信息
        TbManager manager = this.tbManagerMapper.getMangerByEmployeeCode(employeeCode);
        //若当前设备的状态为使用中，查询出该设备的使用记录，并更新使用记录的使用结束时间和使用时长
        if(curStatus.equals(CommonConstant.EQUIP_WORK_STATUS_INUSE)){
            handlerWhenEquipInUse(manager,equipNo);
        }
        if(curStatus.equals(CommonConstant.EQUIP_WORK_STATUS_INCHARGE)){
            handlerWhenEquipInCharge(manager,equipNo);
        }
        if(CommonConstant.EQUIP_WORK_STATUS_INDAMAGE.equals(operateCode)){
            handlerWhenOperateCodeIsInDamage(equip,manager,employeeCode);
        }
        if(CommonConstant.EQUIP_WORK_STATUS_INCHARGE.equals(operateCode)){
            handlerWhenOperateCodeIsInCharge(equip,manager,employeeCode);
        }
        if(CommonConstant.EQUIP_WORK_STATUS_INSTOP.equals(operateCode)){
            //修改设备信息表设备工作状态
            this.equipMapper.updateEquipWorkStatusByEquipNoAndStatus(equipNo,CommonConstant.EQUIP_WORK_STATUS_INSTOP);
        }
    }



    @Override
    public void updateEquipStatusInManagerMode(UpdateUsingEquipStatusReq updateUsingEquipStatusReq) {
        String employeeCode = updateUsingEquipStatusReq.getEmployeeCode();
        String equipNo = updateUsingEquipStatusReq.getEquipNo();
        String operateCode = updateUsingEquipStatusReq.getOperateCode();
        //获取当前设备状态
        TbEquip equip =this.equipMapper.selectEquipByEquipNo(equipNo);
        String curStatus = equip.getEquipWorkState();
//        GetEquipStatusResp esr = this.equipRestMapper.getEquipStatus(equipNo);
//        String curStatus = esr.getStatus();
        //通过工号，获取人员信息
        TbManager manager = this.tbManagerMapper.getMangerByEmployeeCode(employeeCode);
        //若当前设备的状态为使用中，查询出该设备的使用记录，并更新使用记录的使用结束时间和使用时长
        if(curStatus.equals(CommonConstant.EQUIP_WORK_STATUS_INUSE)){
            handlerWhenEquipInUse(manager,equipNo);
        }
        //若当前设备的状态为充电中，查询出该设备的充电记录，并更新使用记录的充电结束时间和充电时长
        if(curStatus.equals(CommonConstant.EQUIP_WORK_STATUS_INCHARGE)){
            handlerWhenEquipInCharge(manager,equipNo);
        }
        if(CommonConstant.EQUIP_WORK_STATUS_INDAMAGE.equals(operateCode)){
            handlerWhenOperateCodeIsInDamage(equip,manager,employeeCode);
        }
        if(CommonConstant.EQUIP_WORK_STATUS_INCHARGE.equals(operateCode)){
            handlerWhenOperateCodeIsInCharge(equip,manager,employeeCode);
        }
        if(CommonConstant.EQUIP_WORK_STATUS_INSTOP.equals(operateCode)){
            //修改设备信息表设备工作状态
            this.equipMapper.updateEquipWorkStatusByEquipNoAndStatus(equipNo,CommonConstant.EQUIP_WORK_STATUS_INSTOP);
        }
        if(CommonConstant.EQUIP_WORK_STATUS_INUSE.equals(operateCode)){
            //修改设备信息表设备工作状态
            handlerWhenOperateCodeIsInUse(equip,manager,employeeCode);
        }
    }

    @Override
    public void updateDamageEquipStatusInManagerMode(UpdateUsingEquipStatusReq updateUsingEquipStatusReq) {
        String employeeCode = updateUsingEquipStatusReq.getEmployeeCode();
        String equipNo = updateUsingEquipStatusReq.getEquipNo();
        String operateCode = updateUsingEquipStatusReq.getOperateCode();
        //获取当前设备状态
        TbEquip equip =this.equipMapper.selectEquipByEquipNo(equipNo);
        String curStatus = equip.getEquipWorkState();
        //通过工号，获取人员信息
        TbManager manager = this.tbManagerMapper.getMangerByEmployeeCode(employeeCode);
        //控制判断，判断设备当前状态是否为损坏
        if(curStatus.equals(CommonConstant.EQUIP_WORK_STATUS_INDAMAGE)){
            if(CommonConstant.EQUIP_WORK_STATUS_INREPAIR.equals(operateCode)){
                handlerWhenOperateCodeIsInRepair(equip,manager,employeeCode);
            }
//            if(CommonConstant.EQUIP_WORK_STATUS_INABORT.equals(operateCode)){
//                handlerWhenOperateCodeIsInabort(equip,manager,employeeCode);
//            }
        }
        if(curStatus.equals(CommonConstant.EQUIP_WORK_STATUS_INREPAIR)){
            if(CommonConstant.EQUIP_WORK_STATUS_INSTOP.equals(operateCode)){
                handlerWhenOperateCodeIsInRepair(equip,manager,employeeCode);
            }
            if(CommonConstant.EQUIP_WORK_STATUS_INABORT.equals(operateCode)){
                handlerWhenOperateCodeIsInabort(equip,manager,employeeCode);
            }
        }
    }

    /**
     *
     * @Description: 将时间、图片路径加入equipListResp
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
        String url = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");

        if(tempList.size()>0){
            String shift = Objects.toString(tempList.get(0).get("shift"));
            Map<String,String> shiftTime = this.equipRestMapper.getShiftTimeByShift(shift);
            String startTime = shiftTime.get("startTime").substring(0,5);
            String endTime = shiftTime.get("endTime").substring(0,5);
            for(EquipListResp ep : list){
                StringBuilder sb = new StringBuilder("");
                ep.setUseTime(sb.append(startTime).append("-").append(endTime).toString());
                ep.setUrl(url+ep.getUrl());
            }
        }else{
            StringBuilder sb = new StringBuilder("");
            for(EquipListResp ep : list){
                ep.setUseTime("无排班");
                ep.setUrl(url+ep.getUrl());
            }
        }
        return list;
    }


    /**
     *
     * @Description: 将时间、图片路径加入ListUnrevertEquipResp
     *
     *
     * @MethodName: rebuildEquipListWithUseTime
     * @Parameters: [list, employeeCode]
     * @ReturnType: java.util.List<com.qcap.cac.dto.EquipListResp>
     *
     * @author huangxiang
     * @date 2018/10/25 16:55
     */
    private List<ListUnrevertEquipResp> rebuildUnrevertEquipListWithUseTime(List<ListUnrevertEquipResp> list, String employeeCode) {
        List<Map<String,Object>> tempList = this.tempTaskSrvImpl.selectCurrountWorkingEmployee(employeeCode);
        String url = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");

        if(tempList.size()>0){
            String shift = Objects.toString(tempList.get(0).get("shift"));
            Map<String,String> shiftTime = this.equipRestMapper.getShiftTimeByShift(shift);
            String startTime = shiftTime.get("startTime").substring(0,5);
            String endTime = shiftTime.get("endTime").substring(0,5);
            for(ListUnrevertEquipResp uer : list){
                StringBuilder sb = new StringBuilder("");
                uer.setStatusName(CommonConstant.EQUIP_WORK_STATUS.get(uer.getStatus()));
                uer.setUseTime(sb.append(startTime).append("-").append(endTime).toString());
                uer.setUrl(url+uer.getUrl());
            }
        }else{
            StringBuilder sb = new StringBuilder("");
            for(ListUnrevertEquipResp uer : list){
                uer.setUseTime("无排班");
                uer.setUrl(url+uer.getUrl());
            }
        }
        return list;
    }

    /**
     *
     * @Description: 若设备当前状态为INUSE(使用中)，先更新设备使用记录，再插入设备使用记录
     *
     *
     * @MethodName: handlerWhenEquipInUse
     * @Parameters: [manager, equipNo] 
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/27 10:13
     */
    private void handlerWhenEquipInUse(TbManager manager,String equipNo){
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
        //更新设备充电记录中结束使用时间和使用时长
        this.equipUseMapper.updateEquipUseByEquipUseId(eu);
    }

    /**
     *
     * @Description: 若设备当前状态为INCHARGE(充电中)，先更新设备充电记录，再插入设备使用记录
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
     * @Description: 若设备的操作代码为INDAMAGE(损坏)，将设备表的状态修改为损坏中,并推送给设备管理员
     *
     *
     * @MethodName: handlerWhenOperateCodeIsInDamage
     * @Parameters: [equip, manager, employeeCode] 
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/27 10:24
     */
    private void handlerWhenOperateCodeIsInDamage(TbEquip equip, TbManager manager, String employeeCode) {
        this.equipMapper.updateEquipWorkStatusByEquipNoAndStatus(equip.getEquipNo(),CommonConstant.EQUIP_WORK_STATUS_INDAMAGE);
        String responseNo = equip.getResponseNo();
        String equipName = equip.getEquipName();
        String equipNo = equip.getEquipNo();
        JpushTools.pushSingle(responseNo,equipName+"("+equipNo+")"+"出现故障，请前往查看！");
    }

    /**
     *
     * @Description: 若设备的操作代码为INCHARGE(充电)，将设备表的状态修改为充电中，并新增一条设备充电记录
     *
     *
     * @MethodName: handlerWhenOperateCodeIsInCharge
     * @Parameters: [equip, manager, employeeCode] 
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/27 10:32
     */
    private void handlerWhenOperateCodeIsInCharge(TbEquip equip, TbManager manager, String employeeCode) {
        TbEquipCharge equipCharge = new TbEquipCharge();
        BeanUtils.copyProperties(equip,equipCharge);
        //重组equipCharge对象
        equipCharge.setEquipChargeId(UUIDUtils.getUUID());
        equipCharge.setStatus(CommonConstant.EQUIP_CHARGE_STATUS_INCHARGE);
        equipCharge.setCreateEmp(employeeCode);
        equipCharge.setUpdateEmp(employeeCode);
        equipCharge.setPersonMobile(manager.getPhone());
        equipCharge.setPersonNo(manager.getAccount());
        equipCharge.setPersonName(manager.getName());
        equipCharge.setStartChargeTime(new Date());
        //新增一条设备充电记录
        this.equipChargeMapper.insert(equipCharge);
        //修改设备信息表设备工作状态
        this.equipMapper.updateEquipWorkStatusByEquipNoAndStatus(equip.getEquipNo(),CommonConstant.EQUIP_WORK_STATUS_INCHARGE);
    }

    /**
     *
     * @Description: 若设备的操作代码为INUSE(使用)，将设备表的状态修改为使用中，并新增一条设备使用记录
     *
     *
     * @MethodName: handlerWhenOperateCodeIsInUse
     * @Parameters: [equip, manager, employeeCode] 
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/27 11:15
     */
    private void handlerWhenOperateCodeIsInUse(TbEquip equip, TbManager manager, String employeeCode) {
        TbEquipUse equipUse = new TbEquipUse();
        BeanUtils.copyProperties(equip,equipUse);
        //重组equipUse对象
        equipUse.setEquipUseId(UUIDUtils.getUUID());
        equipUse.setStatus(CommonConstant.EQUIP_USE_STATUS_INUSE);
        equipUse.setCreateEmp(employeeCode);
        equipUse.setUpdateEmp(employeeCode);
        equipUse.setPersonMobile(manager.getPhone());
        equipUse.setPersonNo(manager.getAccount());
        equipUse.setPersonName(manager.getName());
        equipUse.setStartUseTime(new Date());
        //新增设备使用记录
        this.equipUseMapper.insertEquipUse(equipUse);
        //通过设备编号将设备信息表中的设备工作状态改为使用中
        this.equipMapper.updateEquipWorkStatusByEquipNoAndStatus(equip.getEquipNo(),CommonConstant.EQUIP_WORK_STATUS_INUSE);
    }

    /**
     *
     * @Description: 若设备的操作代码为INREPAIR(维修)，将设备表的状态修改为维修中，并新增一条设备报修记录
     *
     *
     * @MethodName: handlerWhenOperateCodeIsInRepair
     * @Parameters: [equip, manager, employeeCode]
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/27 13:06
     */
    private void handlerWhenOperateCodeIsInRepair(TbEquip equip, TbManager manager, String employeeCode) {
        TbEquipRepair equipRepair = new TbEquipRepair();
        BeanUtils.copyProperties(equip,equipRepair);
        //重组equipRepair对象
        equipRepair.setEquipRepairId(UUIDUtils.getUUID());
        equipRepair.setStatus(CommonConstant.EQUIP_WORK_STATUS_INREPAIR);
        equipRepair.setCreateEmp(employeeCode);
        equipRepair.setUpdateEmp(employeeCode);
        equipRepair.setPersonMobile(manager.getPhone());
        equipRepair.setPersonNo(manager.getAccount());
        equipRepair.setPersonName(manager.getName());
        equipRepair.setRepairTime(new Date());
        //新增一条设备报修记录
        this.equipRepairMapper.insert(equipRepair);
        //通过设备编号将设备信息表中的设备工作状态改为使用中
        this.equipMapper.updateEquipWorkStatusByEquipNoAndStatus(equip.getEquipNo(),CommonConstant.EQUIP_WORK_STATUS_INREPAIR);
    }


    /**
     *
     * @Description: 若设备的操作代码为INABORT(报废)，将设备表的状态修改为报废
     *
     *
     * @MethodName: handlerWhenOperateCodeIsInabort
     * @Parameters: [equip, manager, employeeCode] 
     * @ReturnType: void
     *
     * @author huangxiang
     * @date 2018/10/27 13:27
     */
    private void handlerWhenOperateCodeIsInabort(TbEquip equip, TbManager manager, String employeeCode) {
        this.equipMapper.updateEquipStatusAndWorkStatusByEquipNoAndStatus(equip.getEquipNo(),CommonConstant.EQUIP_STATUS_ABORT,CommonConstant.EQUIP_WORK_STATUS_INABORT);
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
