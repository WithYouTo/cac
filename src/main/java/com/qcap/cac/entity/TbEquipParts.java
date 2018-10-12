package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-12
 */
@TableName("tb_equip_parts")
public class TbEquipParts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String partsId;
    /**
     * 设备ID
     */
    private String equipId;
    /**
     * 配件编号
     */
    private String partsNo;
    /**
     * 配件名称
     */
    private String partsName;
    /**
     * 配件类别
     */
    private String partsType;
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
    private LocalDate latestMaintTime;
    /**
     * 下次维保时间
     */
    private LocalDate nextMaintTime;
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


    public String getPartsId() {
        return partsId;
    }

    public void setPartsId(String partsId) {
        this.partsId = partsId;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
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

    public String getPartsType() {
        return partsType;
    }

    public void setPartsType(String partsType) {
        this.partsType = partsType;
    }

    public String getPartsModel() {
        return partsModel;
    }

    public void setPartsModel(String partsModel) {
        this.partsModel = partsModel;
    }

    public String getMaintCycle() {
        return maintCycle;
    }

    public void setMaintCycle(String maintCycle) {
        this.maintCycle = maintCycle;
    }

    public LocalDate getLatestMaintTime() {
        return latestMaintTime;
    }

    public void setLatestMaintTime(LocalDate latestMaintTime) {
        this.latestMaintTime = latestMaintTime;
    }

    public LocalDate getNextMaintTime() {
        return nextMaintTime;
    }

    public void setNextMaintTime(LocalDate nextMaintTime) {
        this.nextMaintTime = nextMaintTime;
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
        return "TbEquipParts{" +
        ", partsId=" + partsId +
        ", equipId=" + equipId +
        ", partsNo=" + partsNo +
        ", partsName=" + partsName +
        ", partsType=" + partsType +
        ", partsModel=" + partsModel +
        ", maintCycle=" + maintCycle +
        ", latestMaintTime=" + latestMaintTime +
        ", nextMaintTime=" + nextMaintTime +
        ", remark=" + remark +
        ", createEmp=" + createEmp +
        ", createDate=" + createDate +
        ", updateEmp=" + updateEmp +
        ", updateDate=" + updateDate +
        ", version=" + version +
        "}";
    }
}
