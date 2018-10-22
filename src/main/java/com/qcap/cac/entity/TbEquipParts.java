package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-15
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
     * 设备编号
     */
    private String equipNo;
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
     * 供应商
     */
    private String partsAgence;
    /**
     * 维保周期
     */
    private String maintCycle;
    /**
     * 开始使用时间
     */
    private Date startUseTime;
    /**
     * 最近维保时间
     */
    private Date latestMaintTime;
    /**
     * 下次维保时间
     */
    private Date nextMaintTime;
    /**
     * 联系人
     */
    private String relatePerson;
    /**
     * 联系方式
     */
    private String relateMobile;
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
    private int version;

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

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
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

    public String getPartsAgence() {
        return partsAgence;
    }

    public void setPartsAgence(String partsAgence) {
        this.partsAgence = partsAgence;
    }

    public String getMaintCycle() {
        return maintCycle;
    }

    public void setMaintCycle(String maintCycle) {
        this.maintCycle = maintCycle;
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

    public String getRelatePerson() {
        return relatePerson;
    }

    public void setRelatePerson(String relatePerson) {
        this.relatePerson = relatePerson;
    }

    public String getRelateMobile() {
        return relateMobile;
    }

    public void setRelateMobile(String relateMobile) {
        this.relateMobile = relateMobile;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(Date startUseTime) {
        this.startUseTime = startUseTime;
    }

    @Override
    public String toString() {
        return "TbEquipParts{" +
                "partsId='" + partsId + '\'' +
                ", equipId='" + equipId + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", partsNo='" + partsNo + '\'' +
                ", partsName='" + partsName + '\'' +
                ", partsType='" + partsType + '\'' +
                ", partsModel='" + partsModel + '\'' +
                ", partsAgence='" + partsAgence + '\'' +
                ", maintCycle='" + maintCycle + '\'' +
                ", startUseTime=" + startUseTime +
                ", latestMaintTime=" + latestMaintTime +
                ", nextMaintTime=" + nextMaintTime +
                ", relatePerson='" + relatePerson + '\'' +
                ", relateMobile='" + relateMobile + '\'' +
                ", remark='" + remark + '\'' +
                ", createEmp='" + createEmp + '\'' +
                ", createDate=" + createDate +
                ", updateEmp='" + updateEmp + '\'' +
                ", updateDate=" + updateDate +
                ", version=" + version +
                '}';
    }
}
