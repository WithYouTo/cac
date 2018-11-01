package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class AppTaskQueryArrangeRestReq {

    @NotBlank(message = "班次不能为空")
    @ApiModelProperty(value = "班次",required = true)
    private String shift;

    @NotBlank(message = "调班日期不能为空")
    @ApiModelProperty(value = "调班日期",required = true)
    private String workingDate;

    @NotBlank(message = "员工工号不能为空")
    @ApiModelProperty(value = "员工工号",required = true)
    private String employeeCode;

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

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
