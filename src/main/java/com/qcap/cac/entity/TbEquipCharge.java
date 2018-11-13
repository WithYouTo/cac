package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 设备充电记录表
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-15
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
     * 项目编号
     */
    private String programCode;
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
    private Date startChargeTime;
    /**
     * 充电结束时间
     */
    private Date endChargeTime;
    /**
     * 充电时长
     */
    private String totalChargeTime;
    /**
     * 区域
     */
    private String area;
    private String personNo;
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

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
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

    public Date getStartChargeTime() {
        return startChargeTime;
    }

    public void setStartChargeTime(Date startChargeTime) {
        this.startChargeTime = startChargeTime;
    }

    public Date getEndChargeTime() {
        return endChargeTime;
    }

    public void setEndChargeTime(Date endChargeTime) {
        this.endChargeTime = endChargeTime;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
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
                "equipChargeId='" + equipChargeId + '\'' +
                ", equipId='" + equipId + '\'' +
                ", programCode='" + programCode + '\'' +
                ", equipName='" + equipName + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", equipType='" + equipType + '\'' +
                ", equipModel='" + equipModel + '\'' +
                ", startChargeTime=" + startChargeTime +
                ", endChargeTime=" + endChargeTime +
                ", totalChargeTime='" + totalChargeTime + '\'' +
                ", area='" + area + '\'' +
                ", personNo='" + personNo + '\'' +
                ", personName='" + personName + '\'' +
                ", personMobile='" + personMobile + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", createEmp='" + createEmp + '\'' +
                ", createDate=" + createDate +
                ", updateEmp='" + updateEmp + '\'' +
                ", updateDate=" + updateDate +
                ", version=" + version +
                '}';
    }
}
