package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UpdateStopEquipStatusReq {

    @NotBlank(message = "工号不能为空")
    @ApiModelProperty(value = "工号")
    private String employeeCode;

    @NotBlank(message = "设备编号不能为空")
    @ApiModelProperty(value = "设备编号")
    private String equipNo;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    @Override
    public String toString() {
        return "UpdateStopEquipStatusReq{" +
                "employeeCode='" + employeeCode + '\'' +
                ", equipNo='" + equipNo + '\'' +
                '}';
    }
}
