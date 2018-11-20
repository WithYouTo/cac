package com.qcap.cac.entity;

import java.util.Date;

public class TbWarehouseEntry {
    /**
     *   入库主键
     */
    private String warehouseEntryId;

    /**
     *   项目编号
     */
    private String programCode;

    /**
     *   储藏室
     */
    private String storeroomId;

    /**
     *   储藏室名称
     */
    private String storeroom;

    /**
     *   生成规则（P +YYYYMMDDHHMMSS）
     */
    private String entryBatchNo;

    /**
     *   物品库存主键
     */
    private String warehouseStockId;

    /**
     *   数量
     */
    private Integer entryNum;

    /**
     *   单位名称
     */
    private String entryUnit;

    /**
     *   总数量
     */
    private Integer sumNum;

    /**
     *   最小单位
     */
    private String minUnit;

    /**
     *   入库时间
     */
    private String entryTime;

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

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public String getWarehouseEntryId() {
        return warehouseEntryId;
    }

    public void setWarehouseEntryId(String warehouseEntryId) {
        this.warehouseEntryId = warehouseEntryId;
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

    public String getEntryBatchNo() {
        return entryBatchNo;
    }

    public void setEntryBatchNo(String entryBatchNo) {
        this.entryBatchNo = entryBatchNo;
    }

    public String getWarehouseStockId() {
        return warehouseStockId;
    }

    public void setWarehouseStockId(String warehouseStockId) {
        this.warehouseStockId = warehouseStockId;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }

    public Integer getSumNum() {
        return sumNum;
    }

    public void setSumNum(Integer sumNum) {
        this.sumNum = sumNum;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
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