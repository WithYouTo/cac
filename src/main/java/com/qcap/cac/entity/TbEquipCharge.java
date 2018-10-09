package com.qcap.cac.entity;

import java.util.Date;

public class TbEquipCharge {
    private String equipChargeId;

    private String equipName;

    private String equipNo;

    private String equipType;

    private String equipModel;

    private Date startChargeTime;

    private Date endChargeTime;

    private String totalChargeTime;

    private String area;

    private String name;

    private String mobile;

    private String status;

    private String remark;

    private String createEmp;

    private Date createDate;

    private String updateEmp;

    private Date updateDate;

    private Integer version;

    public String getEquipChargeId() {
        return equipChargeId;
    }

    public void setEquipChargeId(String equipChargeId) {
        this.equipChargeId = equipChargeId == null ? null : equipChargeId.trim();
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName == null ? null : equipName.trim();
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo == null ? null : equipNo.trim();
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

    public Date getStartChargeTime() {
        return startChargeTime;
    }

    public void setStartChargeTime(Date startChargeTime) {
        this.startChargeTime = startChargeTime;
    }

    public Date getEndChargeTime() {
        return endChargeTime;
    }

    public void setEndChargeTime(Date endChargeTime) {
        this.endChargeTime = endChargeTime;
    }

    public String getTotalChargeTime() {
        return totalChargeTime;
    }

    public void setTotalChargeTime(String totalChargeTime) {
        this.totalChargeTime = totalChargeTime == null ? null : totalChargeTime.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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