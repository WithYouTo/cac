package com.qcap.cac.entity;

import java.util.Date;

public class TbWarehousePurchase {
    /**
     *   采购主键ID
     */
    private String warehousePurchaseId;

    /**
     *   采购类别
     */
    private String buyType;

    /**
     *   采购编号
     */
    private String buyNo;

    /**
     *   物品类别
     */
    private String goodsType;

    /**
     *   物品编码
     */
    private String goodsNo;

    /**
     *   物品名称
     */
    private String goodsName;

    /**
     *   SUPPLIER_NAME_第一阶段保持一致
     */
    private String supplierNo;

    /**
     *   供应商名称
     */
    private String supplierName;

    /**
     *   采购数量
     */
    private Integer buyNum;

    /**
     *   采购单位(和入库单位保持一致)
     */
    private String entryUnit;

    /**
     *   申请采购日期
     */
    private String buyTime;

    /**
     *   备注
     */
    private String remark;

    /**
     *   新增人
     */
    private String createEmp;

    /**
     *   新增时间
     */
    private Date createDate;

    /**
     *   修改人
     */
    private String updateEmp;

    /**
     *   修改时间
     */
    private Date updateDate;

    /**
     *   版本
     */
    private Integer version;

    public String getWarehousePurchaseId() {
        return warehousePurchaseId;
    }

    public void setWarehousePurchaseId(String warehousePurchaseId) {
        this.warehousePurchaseId = warehousePurchaseId;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public String getBuyNo() {
        return buyNo;
    }

    public void setBuyNo(String buyNo) {
        this.buyNo = buyNo;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
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

    public String getSupplierNo() {
        return supplierNo;
    }

    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}