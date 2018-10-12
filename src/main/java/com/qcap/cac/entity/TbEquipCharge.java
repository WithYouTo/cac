package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 设备充电记录表
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-12
 */
@TableName("tb_equip_charge")
public class TbEquipCharge implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String equipChargeId;
    private String equipId;
    /**
     * 设备名称
     */
    private String equipName;
    /**
     * 设备编号
     */
    private String equipNo;
    /**
     * 设备类别
     */
    private String equipType;
    /**
     * 设备型号
     */
    private String equipModel;
    /**
     * 充电开始时间
     */
    private LocalDateTime startChargeTime;
    /**
     * 充电结束时间
     */
    private LocalDateTime endChargeTime;
    /**
     * 充电时长
     */
    private String totalChargeTime;
    /**
     * 区域
     */
    private String area;
    /**
     * 操作人姓名
     */
    private String personName;
    /**
     * 操作人电话
     */
    private String personMobile;
    /**
     * 设备状态
     */
    private String status;
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


    public String getEquipChargeId() {
        return equipChargeId;
    }

    public void setEquipChargeId(String equipChargeId) {
        this.equipChargeId = equipChargeId;
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

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
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

    public LocalDateTime getStartChargeTime() {
        return startChargeTime;
    }

    public void setStartChargeTime(LocalDateTime startChargeTime) {
        this.startChargeTime = startChargeTime;
    }

    public LocalDateTime getEndChargeTime() {
        return endChargeTime;
    }

    public void setEndChargeTime(LocalDateTime endChargeTime) {
        this.endChargeTime = endChargeTime;
    }

    public String getTotalChargeTime() {
        return totalChargeTime;
    }

    public void setTotalChargeTime(String totalChargeTime) {
        this.totalChargeTime = totalChargeTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "TbEquipCharge{" +
        ", equipChargeId=" + equipChargeId +
        ", equipId=" + equipId +
        ", equipName=" + equipName +
        ", equipNo=" + equipNo +
        ", equipType=" + equipType +
        ", equipModel=" + equipModel +
        ", startChargeTime=" + startChargeTime +
        ", endChargeTime=" + endChargeTime +
        ", totalChargeTime=" + totalChargeTime +
        ", area=" + area +
        ", personName=" + personName +
        ", personMobile=" + personMobile +
        ", status=" + status +
        ", remark=" + remark +
        ", createEmp=" + createEmp +
        ", createDate=" + createDate +
        ", updateEmp=" + updateEmp +
        ", updateDate=" + updateDate +
        ", version=" + version +
        "}";
    }
}
