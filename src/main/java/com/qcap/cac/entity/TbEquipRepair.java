package com.qcap.cac.entity;

import java.util.Date;

public class TbEquipRepair {
    private String equipRepairId;

    private String equipId;

    private String equipNo;

    private String equipName;

    private String equipModel;

    private String equipType;

    private String name;

    private String mobile;

    private String repairTime;

    private String desc;

    private String status;

    private String remark;

    private String createEmp;

    private Date createDate;

    private String updateEmp;

    private Date updateDate;

    private Integer version;

    public String getEquipRepairId() {
        return equipRepairId;
    }

    public void setEquipRepairId(String equipRepairId) {
        this.equipRepairId = equipRepairId == null ? null : equipRepairId.trim();
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

    public String getEquipModel() {
        return equipModel;
    }

    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel == null ? null : equipModel.trim();
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType == null ? null : equipType.trim();
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

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime == null ? null : repairTime.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
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