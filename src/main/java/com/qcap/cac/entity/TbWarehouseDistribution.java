package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 物品发放表
 * </p>
 *
 * @author cindy
 * @since 2018-10-23
 */
@TableName("tb_warehouse_distribution")
public class TbWarehouseDistribution implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 物品发放主键ID
     */
    private String warehouseDistributionId;
    /**
     * 物品领用明细主键ID(关联主键)
     */
    private String warehouseReqdetailId;
    /**
     * 物品编码
     */
    private String goodsNo;
    /**
     * 物品名称
     */
    private String goodsName;
    /**
     * 领用区域
     */
    private String areaId;
    /**
     * 领用区域名称
     */
    private String areaName;
    /**
     * 发放数量
     */
    private Integer distrNum;
    /**
     * 发放单位(和最小单位保持一致)
     */
    private String minUnit;
    /**
     * 发放人工号
     */
    private String employeeCode;
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


    public String getWarehouseDistributionId() {
        return warehouseDistributionId;
    }

    public void setWarehouseDistributionId(String warehouseDistributionId) {
        this.warehouseDistributionId = warehouseDistributionId;
    }

    public String getWarehouseReqdetailId() {
        return warehouseReqdetailId;
    }

    public void setWarehouseReqdetailId(String warehouseReqdetailId) {
        this.warehouseReqdetailId = warehouseReqdetailId;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getDistrNum() {
        return distrNum;
    }

    public void setDistrNum(Integer distrNum) {
        this.distrNum = distrNum;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
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
        return "TbWarehouseDistribution{" +
        ", warehouseDistributionId=" + warehouseDistributionId +
        ", warehouseReqdetailId=" + warehouseReqdetailId +
        ", goodsNo=" + goodsNo +
        ", goodsName=" + goodsName +
        ", areaId=" + areaId +
        ", areaName=" + areaName +
        ", distrNum=" + distrNum +
        ", minUnit=" + minUnit +
        ", employeeCode=" + employeeCode +
        ", remark=" + remark +
        ", createEmp=" + createEmp +
        ", createDate=" + createDate +
        ", updateEmp=" + updateEmp +
        ", updateDate=" + updateDate +
        ", version=" + version +
        "}";
    }
}
