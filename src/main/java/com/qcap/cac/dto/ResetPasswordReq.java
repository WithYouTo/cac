package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel
public class ResetPasswordReq {

    @NotBlank(message="工号不能为空")
    @ApiModelProperty(value="工号")
    private String employeeCode;

    @NotBlank(message="旧密码不能为空")
    @ApiModelProperty(value="旧密码")
    private String oldPassword;

    @NotBlank(message="新密码不能为空")
    @ApiModelProperty(value="新密码")
    private String newPassword;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ResetPasswordReq{" +
                "employeeCode='" + employeeCode + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
