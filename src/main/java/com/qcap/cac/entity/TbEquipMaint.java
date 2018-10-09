package com.qcap.cac.entity;

import java.util.Date;

public class TbEquipMaint {
    private String equipMaintId;

    private String equipId;

    private String equipNo;

    private String equipName;

    private String equipType;

    private String equipModel;

    private String buliding;

    private String floor;

    private String area;

    private String equipUse;

    private Date buyTime;

    private String partsName;

    private String maintType;

    private String equipCycle;

    private Date maintTime;

    private String maintRecord;

    private String maintState;

    private String maintDesc;

    private String remark;

    private String createEmp;

    private Date createDate;

    private String updateEmp;

    private Date updateDate;

    private Integer version;

    public String getEquipMaintId() {
        return equipMaintId;
    }

    public void setEquipMaintId(String equipMaintId) {
        this.equipMaintId = equipMaintId == null ? null : equipMaintId.trim();
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId == null ? null : equipId.trim();
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo == null ? null : equipNo.trim();
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName == null ? null : equipName.trim();
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType == null ? null : equipType.trim();
    }

    public String getEquipModel() {
        return equipModel;
    }

    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel == null ? null : equipModel.trim();
    }

    public String getBuliding() {
        return buliding;
    }

    public void setBuliding(String buliding) {
        this.buliding = buliding == null ? null : buliding.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getEquipUse() {
        return equipUse;
    }

    public void setEquipUse(String equipUse) {
        this.equipUse = equipUse == null ? null : equipUse.trim();
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName == null ? null : partsName.trim();
    }

    public String getMaintType() {
        return maintType;
    }

    public void setMaintType(String maintType) {
        this.maintType = maintType == null ? null : maintType.trim();
    }

    public String getEquipCycle() {
        return equipCycle;
    }

    public void setEquipCycle(String equipCycle) {
        this.equipCycle = equipCycle == null ? null : equipCycle.trim();
    }

    public Date getMaintTime() {
        return maintTime;
    }

    public void setMaintTime(Date maintTime) {
        this.maintTime = maintTime;
    }

    public String getMaintRecord() {
        return maintRecord;
    }

    public void setMaintRecord(String maintRecord) {
        this.maintRecord = maintRecord == null ? null : maintRecord.trim();
    }

    public String getMaintState() {
        return maintState;
    }

    public void setMaintState(String maintState) {
        this.maintState = maintState == null ? null : maintState.trim();
    }

    public String getMaintDesc() {
        return maintDesc;
    }

    public void setMaintDesc(String maintDesc) {
        this.maintDesc = maintDesc == null ? null : maintDesc.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
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
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
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