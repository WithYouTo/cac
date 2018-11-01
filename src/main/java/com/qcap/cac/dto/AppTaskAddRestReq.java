package com.qcap.cac.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class AppTaskAddRestReq {

    @NotBlank(message = "工号不能为空")
    @ApiModelProperty("员工工号")
    private String employeeCode;

    @ApiModelProperty("员工名称")
    private String employeeName;

    @ApiModelProperty("员工电话")
    private String employeeTel;

    @ApiModelProperty("区域编码")
    private String areaCode;

    @ApiModelProperty("区域名称")
    private String areaName;

    @ApiModelProperty("任务描述")
    private String spec;

    @ApiModelProperty("当前登陆人")
    private String createEmp ;


    public String getEmployeeTel() {
        return employeeTel;
    }

    public void setEmployeeTel(String employeeTel) {
        this.employeeTel = employeeTel;
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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

}
