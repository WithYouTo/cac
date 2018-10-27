package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UpdateUsingEquipStatusReq {

    @NotBlank(message = "工号不能为空")
    @ApiModelProperty(value = "工号")
    private String employeeCode;

    @NotBlank(message = "设备编码不能为空")
    @ApiModelProperty(value = "设备编码")
    private String equipNo;

    @NotBlank(message = "操作代码不能为空")
    @ApiModelProperty(value = "操作代码")
    private String operateCode;

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


    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode;
    }

    @Override
    public String toString() {
        return "UpdateUsingEquipStatusReq{" +
                "employeeCode='" + employeeCode + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", operateCode='" + operateCode + '\'' +
                '}';
    }
}
