package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 设备使用记录
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-15
 */
@TableName("tb_equip_use")
public class TbEquipUse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String equipUseId;
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
     * 区域
     */
    private String area;
    /**
     * 使用人工号
     */
    private String personNo;
    /**
     * 使用人姓名
     */
    private String personName;
    /**
     * 使用人电话
     */
    private String personMobile;
    /**
     * 使用开始时间
     */
    private LocalDateTime startUseTime;
    /**
     * 使用结束时间
     */
    private LocalDateTime endUseTime;
    /**
     * 使用时长
     */
    private String totalUseTime;
    /**
     * 状态
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


    public String getEquipUseId() {
        return equipUseId;
    }

    public void setEquipUseId(String equipUseId) {
        this.equipUseId = equipUseId;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPersonNo() {
        return personNo;
    }

    public void setPersonNo(String personNo) {
        this.personNo = personNo;
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

    public LocalDateTime getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(LocalDateTime startUseTime) {
        this.startUseTime = startUseTime;
    }

    public LocalDateTime getEndUseTime() {
        return endUseTime;
    }

    public void setEndUseTime(LocalDateTime endUseTime) {
        this.endUseTime = endUseTime;
    }

    public String getTotalUseTime() {
        return totalUseTime;
    }

    public void setTotalUseTime(String totalUseTime) {
        this.totalUseTime = totalUseTime;
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
        return "TbEquipUse{" +
        ", equipUseId=" + equipUseId +
        ", equipId=" + equipId +
        ", equipNo=" + equipNo +
        ", equipName=" + equipName +
        ", equipType=" + equipType +
        ", equipModel=" + equipModel +
        ", area=" + area +
        ", personNo=" + personNo +
        ", personName=" + personName +
        ", personMobile=" + personMobile +
        ", startUseTime=" + startUseTime +
        ", endUseTime=" + endUseTime +
        ", totalUseTime=" + totalUseTime +
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
