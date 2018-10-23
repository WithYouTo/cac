package com.qcap.cac.dto;

public class GoodsOutReq {

    /**
     * 领用明细主键ID
     */
    private String warehouseReqDetailId;

    /**
     * 实发数量
     */
    private Integer realNum;


    private String employeeCode;

    public String getWarehouseReqDetailId() {
        return warehouseReqDetailId;
    }

    public void setWarehouseReqDetailId(String warehouseReqDetailId) {
        this.warehouseReqDetailId = warehouseReqDetailId;
    }

    public Integer getRealNum() {
        return realNum;
    }

    public void setRealNum(Integer realNum) {
        this.realNum = realNum;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
