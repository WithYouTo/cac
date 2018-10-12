package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 设备保修记录
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-12
 */
@TableName("tb_equip_repair")
public class TbEquipRepair implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备报修ID
     */
    private String equipRepairId;
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
     * 设备型号
     */
    private String equipModel;
    /**
     * 设备类别
     */
    private String equipType;
    /**
     * 报修人姓名
     */
    private String personName;
    /**
     * 报修人联系方式
     */
    private String personMobile;
    /**
     * 报修时间
     */
    private LocalDateTime repairTime;
    /**
     * 描述
     */
    private String equipDesc;
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


    public String getEquipRepairId() {
        return equipRepairId;
    }

    public void setEquipRepairId(String equipRepairId) {
        this.equipRepairId = equipRepairId;
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

    public String getEquipModel() {
        return equipModel;
    }

    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
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

    public LocalDateTime getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(LocalDateTime repairTime) {
        this.repairTime = repairTime;
    }

    public String getEquipDesc() {
        return equipDesc;
    }

    public void setEquipDesc(String equipDesc) {
        this.equipDesc = equipDesc;
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
        return "TbEquipRepair{" +
        ", equipRepairId=" + equipRepairId +
        ", equipId=" + equipId +
        ", equipNo=" + equipNo +
        ", equipName=" + equipName +
        ", equipModel=" + equipModel +
        ", equipType=" + equipType +
        ", personName=" + personName +
        ", personMobile=" + personMobile +
        ", repairTime=" + repairTime +
        ", equipDesc=" + equipDesc +
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
