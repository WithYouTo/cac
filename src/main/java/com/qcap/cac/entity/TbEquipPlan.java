package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备及配件维保计划
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-12
 */
@TableName("tb_equip_plan")
public class TbEquipPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String planId;
    /**
     * 设备ID
     */
    private String equipId;
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
     * 启用日期
     */
    private LocalDate startUseDate;
    /**
     * 生命周期
     */
    private String lifeCycle;
    /**
     * 配件ID
     */
    private String partsId;
    /**
     * 配件编号
     */
    private String partsNo;
    /**
     * 配件名称
     */
    private String partsName;
    /**
     * 维保周期
     */
    private String maintCycle;
    /**
     * 最近维保时间
     */
    private Date latestMaintDate;
    /**
     * 下次维保时间
     */
    private Date nextMaintDate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建人
     */
    private String createEmp;
    /**
     * 创建时间
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
    private String version;


    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
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

    public LocalDate getStartUseDate() {
        return startUseDate;
    }

    public void setStartUseDate(LocalDate startUseDate) {
        this.startUseDate = startUseDate;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getPartsId() {
        return partsId;
    }

    public void setPartsId(String partsId) {
        this.partsId = partsId;
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getMaintCycle() {
        return maintCycle;
    }

    public void setMaintCycle(String maintCycle) {
        this.maintCycle = maintCycle;
    }

    public Date getLatestMaintDate() {
        return latestMaintDate;
    }

    public void setLatestMaintDate(Date latestMaintDate) {
        this.latestMaintDate = latestMaintDate;
    }

    public Date getNextMaintDate() {
        return nextMaintDate;
    }

    public void setNextMaintDate(Date nextMaintDate) {
        this.nextMaintDate = nextMaintDate;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TbEquipPlan{" +
        ", planId=" + planId +
        ", equipId=" + equipId +
        ", equipName=" + equipName +
        ", equipType=" + equipType +
        ", equipModel=" + equipModel +
        ", startUseDate=" + startUseDate +
        ", lifeCycle=" + lifeCycle +
        ", partsId=" + partsId +
        ", partsNo=" + partsNo +
        ", partsName=" + partsName +
        ", maintCycle=" + maintCycle +
        ", latestMaintDate=" + latestMaintDate +
        ", nextMaintDate=" + nextMaintDate +
        ", remark=" + remark +
        ", createEmp=" + createEmp +
        ", createDate=" + createDate +
        ", updateEmp=" + updateEmp +
        ", updateDate=" + updateDate +
        ", version=" + version +
        "}";
    }
}
