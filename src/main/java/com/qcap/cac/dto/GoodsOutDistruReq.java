package com.qcap.cac.dto;

public class GoodsOutDistruReq {

    /**
     * 领用明细主键ID
     */
    private String warehouseReqDetailId;

    private String goodsNo;

    private String goodsName;

    private String positionCode;

    private String positionName;

    private String areaId;

    private String areaName;

    /**
     * 分发数量
     */
    private Integer distrNum;

    private String minUnit;

    private String employeeCode;

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getWarehouseReqDetailId() {
        return warehouseReqDetailId;
    }

    public void setWarehouseReqDetailId(String warehouseReqDetailId) {
        this.warehouseReqDetailId = warehouseReqDetailId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getDistrNum() {
        return distrNum;
    }

    public void setDistrNum(Integer distrNum) {
        this.distrNum = distrNum;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
}
