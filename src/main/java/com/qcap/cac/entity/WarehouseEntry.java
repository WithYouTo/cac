package com.qcap.cac.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 入库表
 * </p>
 *
 * @author cindy
 * @since 2018-10-09
 */
@TableName("tb_warehouse_entry")
public class WarehouseEntry extends Model<WarehouseEntry> implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 入库主键
     */
    @TableId("WAREHOUSE_ENTRY_ID")
    private String warehouseEntryId;
    /**
     * 储藏室
     */
    @TableField("STOREROOM_ID")
    private String storeroomId;
    /**
     * 储藏室名称
     */
    @TableField("STOREROOM")
    private String storeroom;
    /**
     * 生成规则（P +YYYYMMDDHHMMSS）
     */
    @TableField("ENTRY_BATCH_NO")
    private String entryBatchNo;
    /**
     * 物品库存主键
     */
    @TableField("WAREHOUSE_STOCK_ID")
    private String warehouseStockId;
    /**
     * 数量
     */
    @TableField("ENTRY_NUM")
    private Integer entryNum;
    /**
     * 单位名称
     */
    @TableField("ENTRY_UNIT_")
    private String entryUnit;
    /**
     * 总数量
     */
    @TableField("SUM_NUM")
    private BigDecimal sumNum;
    /**
     * 最小单位
     */
    @TableField("MIN_UNIT")
    private String minUnit;
    /**
     * 入库时间
     */
    @TableField("ENTRY_TIME")
    private Date entryTime;
    /**
     * 删除标志(Y删除N未删除)
     */
    @TableField("DELETE_FLAG")
    private String deleteFlag;
    /**
     * 新增人
     */
    @TableField("CREATE_EMP")
    private String createEmp;
    /**
     * 新增时间
     */
    @TableField("CREATE_DATE")
    private Date createDate;
    /**
     * 修改人
     */
    @TableField("UPDATE_EMP")
    private String updateEmp;
    /**
     * 修改时间
     */
    @TableField("UPDATE_DATE")
    private Date updateDate;
    /**
     * 版本
     */
    @TableField("VERSION")
    private Integer version;


    public String getWarehouseEntryId() {
        return warehouseEntryId;
    }

    public void setWarehouseEntryId(String warehouseEntryId) {
        this.warehouseEntryId = warehouseEntryId;
    }

    public String getStoreroomId() {
        return storeroomId;
    }

    public void setStoreroomId(String storeroomId) {
        this.storeroomId = storeroomId;
    }

    public String getStoreroom() {
        return storeroom;
    }

    public void setStoreroom(String storeroom) {
        this.storeroom = storeroom;
    }

    public String getEntryBatchNo() {
        return entryBatchNo;
    }

    public void setEntryBatchNo(String entryBatchNo) {
        this.entryBatchNo = entryBatchNo;
    }

    public String getWarehouseStockId() {
        return warehouseStockId;
    }

    public void setWarehouseStockId(String warehouseStockId) {
        this.warehouseStockId = warehouseStockId;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }

    public BigDecimal getSumNum() {
        return sumNum;
    }

    public void setSumNum(BigDecimal sumNum) {
        this.sumNum = sumNum;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    protected Serializable pkVal() {
        return this.warehouseEntryId;
    }

    @Override
    public String toString() {
        return "WarehouseEntry{" +
        "warehouseEntryId=" + warehouseEntryId +
        ", storeroomId=" + storeroomId +
        ", storeroom=" + storeroom +
        ", entryBatchNo=" + entryBatchNo +
        ", warehouseStockId=" + warehouseStockId +
        ", entryNum=" + entryNum +
        ", entryUnit=" + entryUnit +
        ", sumNum=" + sumNum +
        ", minUnit=" + minUnit +
        ", entryTime=" + entryTime +
        ", deleteFlag=" + deleteFlag +
        ", createEmp=" + createEmp +
        ", createDate=" + createDate +
        ", updateEmp=" + updateEmp +
        ", updateDate=" + updateDate +
        ", version=" + version +
        "}";
    }
}
