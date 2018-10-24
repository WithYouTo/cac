package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class UserListReq {

    @ApiModelProperty(value="组织编码")
    private String orgCode;

    @ApiModelProperty(value="岗位编码")
    private String positionCode;

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    @Override
    public String toString() {
        return "UserListReq{" +
                "orgCode='" + orgCode + '\'' +
                ", positionCode='" + positionCode + '\'' +
                '}';
    }
}
