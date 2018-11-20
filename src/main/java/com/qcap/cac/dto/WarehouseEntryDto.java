package com.qcap.cac.dto;

public class WarehouseEntryDto {

    private String programCode;
    private String entryBatchNo;
    private String goodsNo;
    private String goodsName;
    private String startDate;
    private String endDate;
    private String supplierName;

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getEntryBatchNo() {
        return entryBatchNo;
    }

    public void setEntryBatchNo(String entryBatchNo) {
        this.entryBatchNo = entryBatchNo;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}



