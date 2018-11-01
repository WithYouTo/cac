package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class AppTaskArrangeShiftRestReq {

    @NotBlank(message = "班次不能为空")
    @ApiModelProperty(value = "班次",required = true)
    private String shift;

    @NotBlank(message = "调班日期不能为空")
    @ApiModelProperty(value = "调班日期",required = true)
    private String workingDate;

    @NotBlank(message = "被调班人所在的岗位编号不能为空")
    @ApiModelProperty(value = "被调班人所在的岗位编号",required = true)
    private String positionCode;

    @NotBlank(message = "被调班人所在的岗位名称不能为空")
    @ApiModelProperty(value = "被调班人所在的岗位名称",required = true)
    private String positionName;

    @NotBlank(message = "被调人工号不能为空")
    @ApiModelProperty(value = "被调人工号",required = true)
    private String employeeCode;

    @NotBlank(message = "被调人姓名不能为空")
    @ApiModelProperty(value = "被调人姓名",required = true)
    private String employeeName;

    @NotBlank(message = "调班开始时间不能为空")
    @ApiModelProperty(value = "调班开始时间",required = true)
    private String startTimeStr;

    @NotBlank(message = "调班结束时间不能为空")
    @ApiModelProperty(value = "调班结束时间",required = true)
    private String endTimeStr;

    @NotBlank(message = "顶班人工号不能为空")
    @ApiModelProperty(value = "顶班人工号",required = true)
    private String extraEmployeeCode;

    @NotBlank(message = "顶班人姓名不能为空")
    @ApiModelProperty(value = "顶班人姓名",required = true)
    private String extraEmployeeName;

    @ApiModelProperty(value = "顶班人电话")
    private String extraEmployeeTel;

    @NotBlank(message = "登录人名称不能为空")
    @ApiModelProperty(value = "登录人名称",required = true)
    private String loginName;

    public String getExtraEmployeeTel() {
        return extraEmployeeTel;
    }

    public void setExtraEmployeeTel(String extraEmployeeTel) {
        this.extraEmployeeTel = extraEmployeeTel;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getWorkingDate() {
        return workingDate;
    }

    public void setWorkingDate(String workingDate) {
        this.workingDate = workingDate;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getExtraEmployeeCode() {
        return extraEmployeeCode;
    }

    public void setExtraEmployeeCode(String extraEmployeeCode) {
        this.extraEmployeeCode = extraEmployeeCode;
    }

    public String getExtraEmployeeName() {
        return extraEmployeeName;
    }

    public void setExtraEmployeeName(String extraEmployeeName) {
        this.extraEmployeeName = extraEmployeeName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
