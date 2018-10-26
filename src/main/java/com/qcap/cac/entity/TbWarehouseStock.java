package com.qcap.cac.entity;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class TbWarehouseStock {
    /**
     *   主键ID
     */
    @NotBlank
    private String warehouseStockId;

    /**
     *   储藏室
     */
    private String storeroomId;

    /**
     *   储藏室名称
     */
    private String storeroom;

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
     *   最低警戒线
     */
    private Integer limitStore;

    /**
     *   采购类别
     */
    private String buyType;

    /**
     *   采购编号
     */
    private String buyNo;

    /**
     *   采购数量
     */
    private Integer buyNum;

    /**
     *   采购单位
     */
    private String entryUnit;

    /**
     *   SUPPLIER_NAME_第一阶段保持一致
     */
    private String supplierNo;

    /**
     *   供应商名称
     */
    private String supplierName;

    /**
     *   库存数量
     */
    private Integer goodsNum;

    /**
     *   库存单位
     */
    private String minUnit;

    /**
     *   库存说明
     */
    private String stockInstrution;

    /**
     *   备注
     */
    private String remark;

    /**
     *   删除标志(Y删除N未删除)
     */
    private String deleteFlag;

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

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public String getWarehouseStockId() {
        return warehouseStockId;
    }

    public void setWarehouseStockId(String warehouseStockId) {
        this.warehouseStockId = warehouseStockId;
    }

    public String getStoreroomId() {
        return storeroomId;
    }

    public void setStoreroomId(String storeroomId) {
        this.storeroomId = storeroomId;
    }

    public String getStoreroom() {
        return storeroom;
    }

    public void setStoreroom(String storeroom) {
        this.storeroom = storeroom;
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

    public Integer getLimitStore() {
        return limitStore;
    }

    public void setLimitStore(Integer limitStore) {
        this.limitStore = limitStore;
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

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getStockInstrution() {
        return stockInstrution;
    }

    public void setStockInstrution(String stockInstrution) {
        this.stockInstrution = stockInstrution;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
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