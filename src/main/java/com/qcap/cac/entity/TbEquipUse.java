package com.qcap.cac.entity;

import java.util.Date;

public class TbEquipUse {
    private String equipUseId;

    private String equipId;

    private String equipNo;

    private String equipName;

    private String equipType;

    private String equipModel;

    private String area;

    private String useNo;

    private String useName;

    private String useTel;

    private Date startUseTime;

    private Date endUseTime;

    private String totalUseTime;

    private String status;

    private String remark;

    private String createEmp;

    private Date createDate;

    private String updateEmp;

    private Date updateDate;

    private Integer version;

    public String getEquipUseId() {
        return equipUseId;
    }

    public void setEquipUseId(String equipUseId) {
        this.equipUseId = equipUseId == null ? null : equipUseId.trim();
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getUseNo() {
        return useNo;
    }

    public void setUseNo(String useNo) {
        this.useNo = useNo == null ? null : useNo.trim();
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName == null ? null : useName.trim();
    }

    public String getUseTel() {
        return useTel;
    }

    public void setUseTel(String useTel) {
        this.useTel = useTel == null ? null : useTel.trim();
    }

    public Date getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(Date startUseTime) {
        this.startUseTime = startUseTime;
    }

    public Date getEndUseTime() {
        return endUseTime;
    }

    public void setEndUseTime(Date endUseTime) {
        this.endUseTime = endUseTime;
    }

    public String getTotalUseTime() {
        return totalUseTime;
    }

    public void setTotalUseTime(String totalUseTime) {
        this.totalUseTime = totalUseTime == null ? null : totalUseTime.trim();
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