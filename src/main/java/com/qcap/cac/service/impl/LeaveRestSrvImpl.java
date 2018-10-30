package com.qcap.cac.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.LeaveRestMapper;
import com.qcap.cac.dao.LoginRestMapper;
import com.qcap.cac.dto.AppLeaveApplyReq;
import com.qcap.cac.dto.AppLeaveReq;
import com.qcap.cac.dto.UserListResp;
import com.qcap.cac.entity.TbLeave;
import com.qcap.cac.service.LeaveRestSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.dao.TbManagerMapper;
import com.qcap.core.entity.TbManager;
import com.qcap.core.warpper.FastDFSClientWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class LeaveRestSrvImpl extends ServiceImpl<LeaveRestMapper, TbLeave> implements LeaveRestSrv {

    @Resource
    private LeaveRestMapper leaveRestMapper;

    @Resource
    private LoginRestMapper loginRestMapper;

    @Resource
    private TbManagerMapper managerMapper;

    @Resource
    private FastDFSClientWrapper dfsClient;


    @Override
    public List<AppLeaveReq> queryLeaveList(Map<String, Object> paramMap) {
        return this.leaveRestMapper.queryLeaveList(paramMap);
    }

    @Override
    public List<AppLeaveReq> queryAuditingList(Map<String, Object> paramMap) {

        //具有审核请假权限角色的人
        String employeeCode = ToolUtil.toStr(paramMap.get("employeeCode"));
        if (StringUtils.isEmpty(employeeCode)){
            throw  new RuntimeException("登录人工号为空");
        }

        //查询组织下所有的人员
        String orgCode = this.leaveRestMapper.queryLoginOrgCode(employeeCode);
        List<UserListResp> user = loginRestMapper.getUserListByOrgCode(orgCode);

        List<String> employeeCodeList = user.stream().map(UserListResp::getEmployeeCode).collect(Collectors.toList());
        paramMap.remove("employeeCode");
        paramMap.put("leaveStatus",CommonConstant.LEAVE_STATUS_AUDITING);
        paramMap.put("employeeCodeList",employeeCodeList);
        return this.leaveRestMapper.queryLeaveList(paramMap);
    }

    @Override
    public Integer insertLeaveApply(MultipartHttpServletRequest req, Map<String, MultipartFile> mapFile) throws Exception{

        String employeeCode = req.getParameter("employeeCode");
        if(StringUtils.isEmpty(employeeCode)){
            throw new RuntimeException("用户工号为空");
        }

        TbManager manager = this.managerMapper.getMangerByEmployeeCode(employeeCode);
        if(null == manager){
            throw new RuntimeException("根据用户工号没有查询到用户信息");
        }

        //请假申请参数转化为对象
        AppLeaveApplyReq appLeaveApplyReq = new AppLeaveApplyReq();
        appLeaveApplyReq.setWorkNo(req.getParameter("employeeCode"));
        appLeaveApplyReq.setPersonId(req.getParameter("employeeId"));
        appLeaveApplyReq.setPersonName(manager.getName());
        appLeaveApplyReq.setPersonMobile(manager.getPhone());
        appLeaveApplyReq.setLeaveStartTime(req.getParameter("leaveStartTime"));
        appLeaveApplyReq.setLeaveEndTime(req.getParameter("leaveEndTime"));
        appLeaveApplyReq.setLeaveType(req.getParameter("leaveType"));
        appLeaveApplyReq.setLeaveReason(req.getParameter("leaveReason"));

        //请假时长（以小时为单位）
        Long hour = DateUtil.between(DateUtil.parse(appLeaveApplyReq.getLeaveStartTime()),
                DateUtil.parse(appLeaveApplyReq.getLeaveEndTime()), DateUnit.HOUR);
        appLeaveApplyReq.setLeaveTotalTime(ToolUtil.toStr(hour));

        //存储图片
        String path = fileUploadReturnPath(mapFile);
        appLeaveApplyReq.setLeaveUrl(path);

        TbLeave leave = new TbLeave();
        BeanUtils.copyProperties(appLeaveApplyReq,leave);
        leave.setLeaveId(UUIDUtils.getUUID());
        EntityTools.setCreateEmpAndTime(leave);
        EntityTools.setUpdateEmpAndTime(leave);
        return this.leaveRestMapper.insert(leave);
    }

    @Override
    public AppLeaveReq detailById(String leaveId) {
        if (StringUtils.isEmpty(leaveId)){
            throw  new RuntimeException("请假单主键为空");
        }
        TbLeave leave = this.leaveRestMapper.selectById(leaveId);
        if (null == leave){
            throw  new RuntimeException("根据请假单主键没有查询到信息");
        }
        return this.leaveRestMapper.selectLeaveDetailById(leaveId);
    }

    @Override
    public Integer cancelLeave(Map<String, Object> paramMap) {

        String leaveId = ToolUtil.toStr(paramMap.get("leaveId"));
        String operaType = ToolUtil.toStr(paramMap.get("operaType"));
        if (StringUtils.isEmpty(leaveId)){
            throw  new RuntimeException("请假单主键为空");
        }
        TbLeave leave = this.leaveRestMapper.selectById(leaveId);
        if (null == leave){
            throw  new RuntimeException("根据请假单主键没有查询到信息");
        }
        //判断状态是否是待审批
        if (!leave.getLeaveStatus().equals(CommonConstant.LEAVE_STATUS_AUDITING)){
            throw  new RuntimeException("请假单状态不是待审批");
        }
        if(StringUtils.isEmpty(operaType)){
            throw  new RuntimeException("操作类型不能为空");
        }

        if(CommonConstant.LEAVE_STATUS_CANCEL.equals(operaType)){
            //撤销我的请假单
            leave.setLeaveStatus(CommonConstant.LEAVE_STATUS_CANCEL);
            leave.setAuditTime(DateUtil.formatDateTime(new Date()));
        }else if(CommonConstant.LEAVE_STATUS_PASS.equals(operaType)){
            //审批通过
            leave.setAuditPerson(ToolUtil.toStr(paramMap.get("employeeCode")));
            leave.setAuditTime(DateUtil.formatDateTime(new Date()));
            leave.setLeaveStatus(CommonConstant.LEAVE_STATUS_PASS);
        }else{
            throw  new RuntimeException("操作类型不正确");
        }

        EntityTools.setUpdateEmpAndTime(leave);
        leave.setUpdateEmp(ToolUtil.toStr(paramMap.get("employeeCode")));
        return this.leaveRestMapper.updateById(leave);
    }

    @Override
    public Integer auditLeave(Map<String, MultipartFile> mapFile,String employeeCode,String auditReason,String leaveId) throws Exception {

        if(StringUtils.isEmpty(leaveId)){
            throw  new RuntimeException("请假单主键为空");
        }

        TbLeave leave = new TbLeave();
        leave.setLeaveId(leaveId);
        leave.setRefuseReason(auditReason);
        leave.setAuditPerson(employeeCode);
        leave.setLeaveStatus(CommonConstant.LEAVE_STATUS_REFUSE);
        leave.setAuditTime(DateUtil.formatDateTime(new Date()));
        //存储图片
        String path = fileUploadReturnPath(mapFile);
        leave.setRefuseUrl(path);
        EntityTools.setUpdateEmpAndTime(leave);
        return this.leaveRestMapper.updateById(leave);
    }



    private String fileUploadReturnPath(Map<String, MultipartFile> mapFile) throws Exception{
        // 文件前缀
        String baseUrl = RedisTools.getCommonConfig("CAC_FIPE_PATH_PREFIX");
        if(StringUtils.isEmpty(baseUrl)){
            throw new RuntimeException("系统没有配置文件的访问前缀");
        }
        //存放图片url
        List<String> pathList = new ArrayList<>();
        String path = null;
        if (mapFile != null && !mapFile.isEmpty()) {
            for (Map.Entry<String, MultipartFile> ent : mapFile.entrySet()) {
                MultipartFile mf = ent.getValue();
                String tempPath = dfsClient.uploadFile(mf);
                if(StringUtils.isNotEmpty(tempPath)){
                    pathList.add(baseUrl + tempPath);
                }
            }
            //逗号拼接
            path = pathList.stream().collect(Collectors.joining(","));
        }
        return path;
    }


}
