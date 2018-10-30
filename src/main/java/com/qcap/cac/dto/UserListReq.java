package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserListReq {

    @ApiModelProperty(value="组织编码")
    private String orgCode;

    @ApiModelProperty(value="岗位编码")
    private String positionCode;

    @ApiModelProperty(value="角色编码")
    private String roleNum;

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum;
    }

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
                ", roleNum='" + roleNum + '\'' +
                '}';
    }
}
