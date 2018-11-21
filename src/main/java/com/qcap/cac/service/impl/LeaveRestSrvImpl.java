package com.qcap.cac.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.constant.CommonCodeConstant;
import com.qcap.cac.constant.CommonConstant;
import com.qcap.cac.dao.LeaveRestMapper;
import com.qcap.cac.dao.LoginRestMapper;
import com.qcap.cac.dto.AppLeaveReq;
import com.qcap.cac.dto.UserListResp;
import com.qcap.cac.entity.TbLeave;
import com.qcap.cac.exception.BaseException;
import com.qcap.cac.service.LeaveRestSrv;
import com.qcap.cac.service.MessageRestSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.ToolUtil;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.dao.TbManagerMapper;
import com.qcap.core.entity.TbManager;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.warpper.FastDFSClientWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.*;
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

    @Resource
    private MessageRestSrv messageRestSrv;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        TbLeave leave = new TbLeave();
        leave.setWorkNo(req.getParameter("employeeCode"));
        leave.setPersonId(req.getParameter("employeeId"));
        leave.setPersonName(manager.getName());
        leave.setPersonMobile(manager.getPhone());
        leave.setLeaveStartTime(req.getParameter("leaveStartTime"));
        leave.setLeaveEndTime(req.getParameter("leaveEndTime"));
        leave.setLeaveType(req.getParameter("leaveType"));
        leave.setLeaveReason(req.getParameter("leaveReason"));

        //请假时长（以小时为单位）
        Long hour = DateUtil.between(DateUtil.parse(leave.getLeaveStartTime()),
                DateUtil.parse(leave.getLeaveEndTime()), DateUnit.HOUR);
        leave.setLeaveTotalTime(ToolUtil.toStr(hour));

        //存储图片
        String path = fileUploadReturnPath(mapFile);
        leave.setLeaveUrl(path);

        leave.setLeaveId(UUIDUtils.getUUID());

        List<String>  programCodes = AppUtils.getLoginUserProjectCodes();
        String programCode = "";
        if(!CollectionUtils.isEmpty(programCodes)){
            programCodes.removeAll(Collections.singleton(""));
            programCode = StringUtils.join(programCodes,",");
            leave.setProgramCode(programCode);
        }
        EntityTools.setCreateEmpAndTime(leave);
        EntityTools.setUpdateEmpAndTime(leave);
        this.leaveRestMapper.insert(leave);

        //查询具有审批角色的人员
//        String roleNum = RedisTools.getCommonConfig("CAC_LEAVE_AUDIT_ROLE_NUM");
//        List<UserListResp> userList = loginRestMapper.getUserListByRoleNum(roleNum,programCode);
        List<String> empList = this.getEmpManager( employeeCode);
        //推送消息
        String title = "新的请假单";
        String message = "您有一个111" + manager.getName() +  "的请假单待审批";
        messageRestSrv.JpushMessage(empList, StringUtils.join(programCodes,","),message,title);
        return 1;
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

            //推送消息
            String workNo = leave.getWorkNo();
            List<String> programCodes = AppUtils.getLoginUserProjectCodes();
            String title = "请假单审批通过";
            String leaveStartFormat = DateUtil.format(DateUtil.parseDateTime(leave.getLeaveStartTime()),"yyyy-MM-dd HH:ss");
            String leaveEndFormat = DateUtil.format(DateUtil.parseDateTime(leave.getLeaveEndTime()),"yyyy-MM-dd HH:ss");
            String message = "您的请假单【" + leaveStartFormat + "至" + leaveEndFormat + "】审批通过";
            messageRestSrv.JpushMessage(workNo, StringUtils.join(programCodes,","),message,title);

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
        this.leaveRestMapper.updateById(leave);

        //推送消息
        leave = this.leaveRestMapper.selectById(leaveId);
        String workNo = leave.getWorkNo();
        List<String> programCodes = AppUtils.getLoginUserProjectCodes();
        String title = "请假单被驳回";
        String leaveStartFormat = DateUtil.format(DateUtil.parseDateTime(leave.getLeaveStartTime()),"yyyy-MM-dd HH:ss");
        String leaveEndFormat = DateUtil.format(DateUtil.parseDateTime(leave.getLeaveEndTime()),"yyyy-MM-dd HH:ss");
        String message = "您的请假单【" + leaveStartFormat + "至" + leaveEndFormat + "】被驳回";
        messageRestSrv.JpushMessage(workNo, StringUtils.join(programCodes,","),message,title);
        return 1;
    }
    
    /**
     * 根据清洁人员工号，获取其上级管理人员工号
     * @Title: getEmpManager 
     * @Description: TODO
     * @param employeeCode
     * @return
     * @return: List<String>
     */
    private List<String> getEmpManager(String employeeCode) {
    	String areaCode = this.leaveRestMapper.selectAreaCodeByEmployCode(employeeCode);
    	if(StringUtils.isBlank(areaCode)) {
    		throw new BaseException(CommonCodeConstant.ERROR_CODE_40402, "根据工号未查询到岗位所对应的区域");
    	}
    	String [] areaCodeArr = areaCode.split(",");
    	String sqlHead= "SELECT EMPLOYEE_CODE " + 
		    			"FROM tb_task_arrangement " + 
		    			"WHERE POSITION_CODE =(" + 
		    			"			SELECT POSITION_CODE FROM tb_area_position " + 
		    			"			WHERE POSITION_TYPE !='3' " ;
	    			
    	String sqlFoot ="			LIMIT 1 )";
    	String sqlCondition="";
    	for(String str : areaCodeArr) {
    		sqlCondition += "	AND INSTR(AREA_CODE,'"+str+"') " ;
    	}
    	
    	String sql =sqlHead + sqlCondition + sqlFoot;
    	List<String> mgrEmpList = jdbcTemplate.queryForList(sql, String.class);
		return mgrEmpList;
    	
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
