package com.qcap.cac.entity;

import java.util.Date;

public class TbTask {
    /**
    *   主键
     */
    private String taskId;

    /**
    *   任务编号
     */
    private String taskCode;

    /**
    *   计划ID
     */
    private String planId;

    /**
    *   任务类型
     */
    private String taskType;

    /**
    *   岗位编码
     */
    private String positionCode;

    /**
    *   岗位名称
     */
    private String positionName;

    /**
    *   区域编码
     */
    private String areaCode;

    /**
    *   区域名称
     */
    private String areaName;

    /**
    *   标准编码
     */
    private String standardCode;

    /**
    *   标准名称
     */
    private String standardName;

    /**
    *   班次
     */
    private String shift;

    /**
    *   清洁说明
     */
    private String spec;

    /**
    *   员工编号
     */
    private String employeeCode;

    /**
    *   员工名称
     */
    private String employeeName;

    /**
    *   员工电话
     */
    private String employeeTel;

    /**
    *   任务完成时间
     */
    private Date completeTime;

    /**
    *   计划开始时间
     */
    private Date startTime;

    /**
    *   计划结束时间
     */
    private Date endTime;

    /**
    *   任务状态
     */
    private String taskStatus;

    /**
    *   检查状态
     */
    private String checkStatus;

    /**
    *   任务检查评分
     */
    private Integer taskScore;

    /**
    *   任务是否必须上传图片
     */
    private String uploadPicFlag;

    /**
    *   是否必须检查
     */
    private String checkFlag;

    /**
    *   意见与建议
     */
    private String taskAdvice;

    /**
    *   任务完成描述
     */
    private String taskFinishDesc;

    /**
    *   不合格原因
     */
    private String disqualifiedReason;

    /**
    *   任务提醒时间
     */
    private Date taskRemindTime;

    /**
    *   清洁人员反馈图片路径
     */
    private String feedbackImgUrl;

    /**
    *   检查人员上传图片路径
     */
    private String checkImgUrl;

    /**
    *   任务说明图片
     */
    private String taskImgUrl;

    /**
    *   
     */
    private String lineNo;

    /**
    *   备注1
     */
    private String remark1;

    /**
    *   备注2
     */
    private String remark2;

    /**
    *   备注3
     */
    private String remark3;

    /**
    *   新增人
     */
    private String createEmp;

    /**
    *   新增时间
     */
    private Date createDate;

    /**
    *   修改时间
     */
    private Date updateDate;

    /**
    *   修改人
     */
    private String updateEmp;

    /**
    *   版本
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_ID
     *
     * @return the value of tb_task.TASK_ID
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_ID
     *
     * @param taskId the value for tb_task.TASK_ID
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_CODE
     *
     * @return the value of tb_task.TASK_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_CODE
     *
     * @param taskCode the value for tb_task.TASK_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.PLAN_ID
     *
     * @return the value of tb_task.PLAN_ID
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.PLAN_ID
     *
     * @param planId the value for tb_task.PLAN_ID
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setPlanId(String planId) {
        this.planId = planId == null ? null : planId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_TYPE
     *
     * @return the value of tb_task.TASK_TYPE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getTaskType() {
        return taskType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_TYPE
     *
     * @param taskType the value for tb_task.TASK_TYPE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.POSITION_CODE
     *
     * @return the value of tb_task.POSITION_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.POSITION_CODE
     *
     * @param positionCode the value for tb_task.POSITION_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.POSITION_NAME
     *
     * @return the value of tb_task.POSITION_NAME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.POSITION_NAME
     *
     * @param positionName the value for tb_task.POSITION_NAME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.AREA_CODE
     *
     * @return the value of tb_task.AREA_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.AREA_CODE
     *
     * @param areaCode the value for tb_task.AREA_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.AREA_NAME
     *
     * @return the value of tb_task.AREA_NAME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.AREA_NAME
     *
     * @param areaName the value for tb_task.AREA_NAME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.STANDARD_CODE
     *
     * @return the value of tb_task.STANDARD_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getStandardCode() {
        return standardCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.STANDARD_CODE
     *
     * @param standardCode the value for tb_task.STANDARD_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode == null ? null : standardCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.STANDARD_NAME
     *
     * @return the value of tb_task.STANDARD_NAME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getStandardName() {
        return standardName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.STANDARD_NAME
     *
     * @param standardName the value for tb_task.STANDARD_NAME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setStandardName(String standardName) {
        this.standardName = standardName == null ? null : standardName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.SHIFT
     *
     * @return the value of tb_task.SHIFT
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getShift() {
        return shift;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.SHIFT
     *
     * @param shift the value for tb_task.SHIFT
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setShift(String shift) {
        this.shift = shift == null ? null : shift.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.SPEC
     *
     * @return the value of tb_task.SPEC
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getSpec() {
        return spec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.SPEC
     *
     * @param spec the value for tb_task.SPEC
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.EMPLOYEE_CODE
     *
     * @return the value of tb_task.EMPLOYEE_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getEmployeeCode() {
        return employeeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.EMPLOYEE_CODE
     *
     * @param employeeCode the value for tb_task.EMPLOYEE_CODE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode == null ? null : employeeCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.EMPLOYEE_NAME
     *
     * @return the value of tb_task.EMPLOYEE_NAME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.EMPLOYEE_NAME
     *
     * @param employeeName the value for tb_task.EMPLOYEE_NAME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.EMPLOYEE_TEL
     *
     * @return the value of tb_task.EMPLOYEE_TEL
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getEmployeeTel() {
        return employeeTel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.EMPLOYEE_TEL
     *
     * @param employeeTel the value for tb_task.EMPLOYEE_TEL
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel == null ? null : employeeTel.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.COMPLETE_TIME
     *
     * @return the value of tb_task.COMPLETE_TIME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.COMPLETE_TIME
     *
     * @param completeTime the value for tb_task.COMPLETE_TIME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.START_TIME
     *
     * @return the value of tb_task.START_TIME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.START_TIME
     *
     * @param startTime the value for tb_task.START_TIME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.END_TIME
     *
     * @return the value of tb_task.END_TIME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.END_TIME
     *
     * @param endTime the value for tb_task.END_TIME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_STATUS
     *
     * @return the value of tb_task.TASK_STATUS
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_STATUS
     *
     * @param taskStatus the value for tb_task.TASK_STATUS
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.CHECK_STATUS
     *
     * @return the value of tb_task.CHECK_STATUS
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getCheckStatus() {
        return checkStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.CHECK_STATUS
     *
     * @param checkStatus the value for tb_task.CHECK_STATUS
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus == null ? null : checkStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_SCORE
     *
     * @return the value of tb_task.TASK_SCORE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public Integer getTaskScore() {
        return taskScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_SCORE
     *
     * @param taskScore the value for tb_task.TASK_SCORE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskScore(Integer taskScore) {
        this.taskScore = taskScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.UPLOAD_PIC_FLAG
     *
     * @return the value of tb_task.UPLOAD_PIC_FLAG
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getUploadPicFlag() {
        return uploadPicFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.UPLOAD_PIC_FLAG
     *
     * @param uploadPicFlag the value for tb_task.UPLOAD_PIC_FLAG
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setUploadPicFlag(String uploadPicFlag) {
        this.uploadPicFlag = uploadPicFlag == null ? null : uploadPicFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.CHECK_FLAG
     *
     * @return the value of tb_task.CHECK_FLAG
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.CHECK_FLAG
     *
     * @param checkFlag the value for tb_task.CHECK_FLAG
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag == null ? null : checkFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_ADVICE
     *
     * @return the value of tb_task.TASK_ADVICE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getTaskAdvice() {
        return taskAdvice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_ADVICE
     *
     * @param taskAdvice the value for tb_task.TASK_ADVICE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskAdvice(String taskAdvice) {
        this.taskAdvice = taskAdvice == null ? null : taskAdvice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_FINISH_DESC
     *
     * @return the value of tb_task.TASK_FINISH_DESC
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getTaskFinishDesc() {
        return taskFinishDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_FINISH_DESC
     *
     * @param taskFinishDesc the value for tb_task.TASK_FINISH_DESC
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskFinishDesc(String taskFinishDesc) {
        this.taskFinishDesc = taskFinishDesc == null ? null : taskFinishDesc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.DISQUALIFIED_REASON
     *
     * @return the value of tb_task.DISQUALIFIED_REASON
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getDisqualifiedReason() {
        return disqualifiedReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.DISQUALIFIED_REASON
     *
     * @param disqualifiedReason the value for tb_task.DISQUALIFIED_REASON
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setDisqualifiedReason(String disqualifiedReason) {
        this.disqualifiedReason = disqualifiedReason == null ? null : disqualifiedReason.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_REMIND_TIME
     *
     * @return the value of tb_task.TASK_REMIND_TIME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public Date getTaskRemindTime() {
        return taskRemindTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_REMIND_TIME
     *
     * @param taskRemindTime the value for tb_task.TASK_REMIND_TIME
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskRemindTime(Date taskRemindTime) {
        this.taskRemindTime = taskRemindTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.FEEDBACK_IMG_URL
     *
     * @return the value of tb_task.FEEDBACK_IMG_URL
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getFeedbackImgUrl() {
        return feedbackImgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.FEEDBACK_IMG_URL
     *
     * @param feedbackImgUrl the value for tb_task.FEEDBACK_IMG_URL
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setFeedbackImgUrl(String feedbackImgUrl) {
        this.feedbackImgUrl = feedbackImgUrl == null ? null : feedbackImgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.CHECK_IMG_URL
     *
     * @return the value of tb_task.CHECK_IMG_URL
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getCheckImgUrl() {
        return checkImgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.CHECK_IMG_URL
     *
     * @param checkImgUrl the value for tb_task.CHECK_IMG_URL
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setCheckImgUrl(String checkImgUrl) {
        this.checkImgUrl = checkImgUrl == null ? null : checkImgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.TASK_IMG_URL
     *
     * @return the value of tb_task.TASK_IMG_URL
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getTaskImgUrl() {
        return taskImgUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.TASK_IMG_URL
     *
     * @param taskImgUrl the value for tb_task.TASK_IMG_URL
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setTaskImgUrl(String taskImgUrl) {
        this.taskImgUrl = taskImgUrl == null ? null : taskImgUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.LINE_NO
     *
     * @return the value of tb_task.LINE_NO
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getLineNo() {
        return lineNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.LINE_NO
     *
     * @param lineNo the value for tb_task.LINE_NO
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setLineNo(String lineNo) {
        this.lineNo = lineNo == null ? null : lineNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.REMARK1
     *
     * @return the value of tb_task.REMARK1
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.REMARK1
     *
     * @param remark1 the value for tb_task.REMARK1
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.REMARK2
     *
     * @return the value of tb_task.REMARK2
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.REMARK2
     *
     * @param remark2 the value for tb_task.REMARK2
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.REMARK3
     *
     * @return the value of tb_task.REMARK3
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.REMARK3
     *
     * @param remark3 the value for tb_task.REMARK3
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.CREATE_EMP
     *
     * @return the value of tb_task.CREATE_EMP
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.CREATE_EMP
     *
     * @param createEmp the value for tb_task.CREATE_EMP
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.CREATE_DATE
     *
     * @return the value of tb_task.CREATE_DATE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.CREATE_DATE
     *
     * @param createDate the value for tb_task.CREATE_DATE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.UPDATE_DATE
     *
     * @return the value of tb_task.UPDATE_DATE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.UPDATE_DATE
     *
     * @param updateDate the value for tb_task.UPDATE_DATE
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.UPDATE_EMP
     *
     * @return the value of tb_task.UPDATE_EMP
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.UPDATE_EMP
     *
     * @param updateEmp the value for tb_task.UPDATE_EMP
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_task.VERSION
     *
     * @return the value of tb_task.VERSION
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_task.VERSION
     *
     * @param version the value for tb_task.VERSION
     *
     * @mbggenerated Thu Oct 25 15:17:25 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}