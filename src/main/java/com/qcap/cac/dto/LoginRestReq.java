package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class LoginRestReq {

    @NotBlank(message="工号不能为空")
    @ApiModelProperty(value="工号")
    private String employeeCode;

    @NotBlank(message="密码不能为空")
    @ApiModelProperty(value="密码")
    private String password;

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRestReq{" +
                "employeeCode='" + employeeCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
