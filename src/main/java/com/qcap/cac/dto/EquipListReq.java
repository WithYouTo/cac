package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class EquipListReq {

    @NotBlank(message = "员工工号不能为空")
    @ApiModelProperty(value = "员工工号")
    private String employeeCode;

    @NotBlank(message = "编号不能为空")
    @ApiModelProperty(value = "编号")
    private String lineNo;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    @Override
    public String toString() {
        return "EquipListReq{" +
                "employeeCode='" + employeeCode + '\'' +
                ", lineNo='" + lineNo + '\'' +
                '}';
    }
}
