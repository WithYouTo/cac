package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-15
 */
@TableName("tb_equip_maint")
public class TbEquipMaint implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String equipMaintId;
    /**
     * 设备管理表主键
     */
    private String equipId;
    /**
     * 设备编号
     */
    private String equipNo;
    /**
     * 设备名称
     */
    private String equipName;
    /**
     * 设备类别
     */
    private String equipType;
    /**
     * 设备型号
     */
    private String equipModel;
    /**
     * 配件名称
     */
    private String partsName;
    /**
     * 配件编号
     */
    private String partsNo;
    /**
     * 维保类别
     */
    private String maintType;
    /**
     * 维保周期
     */
    private String equipCycle;
    /**
     * 维保时间
     */
    private LocalDateTime maintTime;
    /**
     * 维保记录
     */
    private String maintRecord;
    /**
     * 维保状态
     */
    private String maintState;
    /**
     * 维保描述
     */
    private String maintDesc;
    /**
     * 备注
     */
    private String remark;
    /**
     * 新增人
     */
    private String createEmp;
    /**
     * 新增时间
     */
    private LocalDateTime createDate;
    /**
     * 修改人
     */
    private String updateEmp;
    /**
     * 修改时间
     */
    private LocalDateTime updateDate;
    /**
     * 版本
     */
    private Integer version;


    public String getEquipMaintId() {
        return equipMaintId;
    }

    public void setEquipMaintId(String equipMaintId) {
        this.equipMaintId = equipMaintId;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipModel() {
        return equipModel;
    }

    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo;
    }

    public String getMaintType() {
        return maintType;
    }

    public void setMaintType(String maintType) {
        this.maintType = maintType;
    }

    public String getEquipCycle() {
        return equipCycle;
    }

    public void setEquipCycle(String equipCycle) {
        this.equipCycle = equipCycle;
    }

    public LocalDateTime getMaintTime() {
        return maintTime;
    }

    public void setMaintTime(LocalDateTime maintTime) {
        this.maintTime = maintTime;
    }

    public String getMaintRecord() {
        return maintRecord;
    }

    public void setMaintRecord(String maintRecord) {
        this.maintRecord = maintRecord;
    }

    public String getMaintState() {
        return maintState;
    }

    public void setMaintState(String maintState) {
        this.maintState = maintState;
    }

    public String getMaintDesc() {
        return maintDesc;
    }

    public void setMaintDesc(String maintDesc) {
        this.maintDesc = maintDesc;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TbEquipMaint{" +
        ", equipMaintId=" + equipMaintId +
        ", equipId=" + equipId +
        ", equipNo=" + equipNo +
        ", equipName=" + equipName +
        ", equipType=" + equipType +
        ", equipModel=" + equipModel +
        ", partsName=" + partsName +
        ", partsNo=" + partsNo +
        ", maintType=" + maintType +
        ", equipCycle=" + equipCycle +
        ", maintTime=" + maintTime +
        ", maintRecord=" + maintRecord +
        ", maintState=" + maintState +
        ", maintDesc=" + maintDesc +
        ", remark=" + remark +
        ", createEmp=" + createEmp +
        ", createDate=" + createDate +
        ", updateEmp=" + updateEmp +
        ", updateDate=" + updateDate +
        ", version=" + version +
        "}";
    }
}
