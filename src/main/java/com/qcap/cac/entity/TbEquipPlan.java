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
 * @since 2018-10-15
 */
@TableName("tb_equip_plan")
public class TbEquipPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String planId;
    /**
     * 项目编码
     */
    private String programCode;
    /**
     * 设备ID
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
     * 启用日期
     */
    private Date startUseTime;
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
     * 配件型号
     */
    private String partsModel;
    /**
     * 维保周期
     */
    private String maintCycle;
    /**
     * 最近维保时间
     */
    private Date latestMaintTime;
    /**
     * 下次维保时间
     */
    private Date nextMaintTime;
    /**
     * 设备维保提醒时间
     */
    private String noticeDate;
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
    private Date createDate;
    /**
     * 修改人
     */
    private String updateEmp;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 版本
     */
    private Integer version;

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

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

    public Date getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(Date startUseTime) {
        this.startUseTime = startUseTime;
    }

    public String getPartsModel() {
        return partsModel;
    }

    public void setPartsModel(String partsModel) {
        this.partsModel = partsModel;
    }

    public Date getLatestMaintTime() {
        return latestMaintTime;
    }

    public void setLatestMaintTime(Date latestMaintTime) {
        this.latestMaintTime = latestMaintTime;
    }

    public Date getNextMaintTime() {
        return nextMaintTime;
    }

    public void setNextMaintTime(Date nextMaintTime) {
        this.nextMaintTime = nextMaintTime;
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

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    @Override
    public String toString() {
        return "TbEquipPlan{" +
                "planId='" + planId + '\'' +
                ", programCode='" + programCode + '\'' +
                ", equipId='" + equipId + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", equipName='" + equipName + '\'' +
                ", equipType='" + equipType + '\'' +
                ", equipModel='" + equipModel + '\'' +
                ", startUseTime=" + startUseTime +
                ", lifeCycle='" + lifeCycle + '\'' +
                ", partsId='" + partsId + '\'' +
                ", partsNo='" + partsNo + '\'' +
                ", partsName='" + partsName + '\'' +
                ", partsModel='" + partsModel + '\'' +
                ", maintCycle='" + maintCycle + '\'' +
                ", latestMaintTime=" + latestMaintTime +
                ", nextMaintTime=" + nextMaintTime +
                ", noticeDate='" + noticeDate + '\'' +
                ", remark='" + remark + '\'' +
                ", createEmp='" + createEmp + '\'' +
                ", createDate=" + createDate +
                ", updateEmp='" + updateEmp + '\'' +
                ", updateDate=" + updateDate +
                ", version=" + version +
                '}';
    }
}
