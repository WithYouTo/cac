package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UpdateEquipStatusReq {

    @NotBlank(message = "设备编号不能为空")
    @ApiModelProperty(value = "设备编号")
    private String equipNo;

    @NotBlank(message = "设备状态不能为空")
    @ApiModelProperty(value = "设备状态")
    private String status;

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdateEquipStatusReq{" +
                "equipNo='" + equipNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
