package com.qcap.cac.entity;

import java.util.Date;

public class TbWarehouseStock {
    /**
     *   主键ID
     */
    private String warehouseStockId;

    /**
     *   储藏室
     */
    private String storeroomId;

    /**
     *   储藏室名称
     */
    private String storeroom;

    /**
     *   物品类别
     */
    private String goodsType;

    /**
     *   物品编码
     */
    private String goodsNo;

    /**
     *   物品名称
     */
    private String goodsName;

    /**
     *   最低警戒线
     */
    private Integer limitStore;

    /**
     *   采购类别
     */
    private String buyType;

    /**
     *   采购编号
     */
    private String buyNo;

    /**
     *   采购数量
     */
    private String buyNum;

    /**
     *   采购单位
     */
    private String entryUnit;

    /**
     *   SUPPLIER_NAME_第一阶段保持一致
     */
    private String supplierNo;

    /**
     *   供应商名称
     */
    private String supplierName;

    /**
     *   库存数量
     */
    private String goodsNum;

    /**
     *   库存说明
     */
    private String stockInstrution;

    /**
     *   备注
     */
    private String remark;

    /**
     *   删除标志(Y删除N未删除)
     */
    private String deleteFlag;

    /**
     *   新增人
     */
    private String createEmp;

    /**
     *   新增时间
     */
    private Date createDate;

    /**
     *   修改人
     */
    private String updateEmp;

    /**
     *   修改时间
     */
    private Date updateDate;

    /**
     *   版本
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.WAREHOUSE_STOCK_ID
     *
     * @return the value of tb_warehouse_stock.WAREHOUSE_STOCK_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getWarehouseStockId() {
        return warehouseStockId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.WAREHOUSE_STOCK_ID
     *
     * @param warehouseStockId the value for tb_warehouse_stock.WAREHOUSE_STOCK_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setWarehouseStockId(String warehouseStockId) {
        this.warehouseStockId = warehouseStockId == null ? null : warehouseStockId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.STOREROOM_ID
     *
     * @return the value of tb_warehouse_stock.STOREROOM_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getStoreroomId() {
        return storeroomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.STOREROOM_ID
     *
     * @param storeroomId the value for tb_warehouse_stock.STOREROOM_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setStoreroomId(String storeroomId) {
        this.storeroomId = storeroomId == null ? null : storeroomId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.STOREROOM
     *
     * @return the value of tb_warehouse_stock.STOREROOM
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getStoreroom() {
        return storeroom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.STOREROOM
     *
     * @param storeroom the value for tb_warehouse_stock.STOREROOM
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setStoreroom(String storeroom) {
        this.storeroom = storeroom == null ? null : storeroom.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.GOODS_TYPE
     *
     * @return the value of tb_warehouse_stock.GOODS_TYPE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getGoodsType() {
        return goodsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.GOODS_TYPE
     *
     * @param goodsType the value for tb_warehouse_stock.GOODS_TYPE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType == null ? null : goodsType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.GOODS_NO
     *
     * @return the value of tb_warehouse_stock.GOODS_NO
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getGoodsNo() {
        return goodsNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.GOODS_NO
     *
     * @param goodsNo the value for tb_warehouse_stock.GOODS_NO
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo == null ? null : goodsNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.GOODS_NAME
     *
     * @return the value of tb_warehouse_stock.GOODS_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.GOODS_NAME
     *
     * @param goodsName the value for tb_warehouse_stock.GOODS_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.LIMIT_STORE
     *
     * @return the value of tb_warehouse_stock.LIMIT_STORE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Integer getLimitStore() {
        return limitStore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.LIMIT_STORE
     *
     * @param limitStore the value for tb_warehouse_stock.LIMIT_STORE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setLimitStore(Integer limitStore) {
        this.limitStore = limitStore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.BUY_TYPE
     *
     * @return the value of tb_warehouse_stock.BUY_TYPE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getBuyType() {
        return buyType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.BUY_TYPE
     *
     * @param buyType the value for tb_warehouse_stock.BUY_TYPE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setBuyType(String buyType) {
        this.buyType = buyType == null ? null : buyType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.BUY_NO
     *
     * @return the value of tb_warehouse_stock.BUY_NO
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getBuyNo() {
        return buyNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.BUY_NO
     *
     * @param buyNo the value for tb_warehouse_stock.BUY_NO
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setBuyNo(String buyNo) {
        this.buyNo = buyNo == null ? null : buyNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.BUY_NUM
     *
     * @return the value of tb_warehouse_stock.BUY_NUM
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getBuyNum() {
        return buyNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.BUY_NUM
     *
     * @param buyNum the value for tb_warehouse_stock.BUY_NUM
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum == null ? null : buyNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.ENTRY_UNIT
     *
     * @return the value of tb_warehouse_stock.ENTRY_UNIT
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getEntryUnit() {
        return entryUnit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.ENTRY_UNIT
     *
     * @param entryUnit the value for tb_warehouse_stock.ENTRY_UNIT
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit == null ? null : entryUnit.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.SUPPLIER_NO
     *
     * @return the value of tb_warehouse_stock.SUPPLIER_NO
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getSupplierNo() {
        return supplierNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.SUPPLIER_NO
     *
     * @param supplierNo the value for tb_warehouse_stock.SUPPLIER_NO
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo == null ? null : supplierNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.SUPPLIER_NAME
     *
     * @return the value of tb_warehouse_stock.SUPPLIER_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.SUPPLIER_NAME
     *
     * @param supplierName the value for tb_warehouse_stock.SUPPLIER_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.GOODS_NUM
     *
     * @return the value of tb_warehouse_stock.GOODS_NUM
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getGoodsNum() {
        return goodsNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.GOODS_NUM
     *
     * @param goodsNum the value for tb_warehouse_stock.GOODS_NUM
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setGoodsNum(String goodsNum) {
        this.goodsNum = goodsNum == null ? null : goodsNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.STOCK_INSTRUTION
     *
     * @return the value of tb_warehouse_stock.STOCK_INSTRUTION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getStockInstrution() {
        return stockInstrution;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.STOCK_INSTRUTION
     *
     * @param stockInstrution the value for tb_warehouse_stock.STOCK_INSTRUTION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setStockInstrution(String stockInstrution) {
        this.stockInstrution = stockInstrution == null ? null : stockInstrution.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.REMARK
     *
     * @return the value of tb_warehouse_stock.REMARK
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.REMARK
     *
     * @param remark the value for tb_warehouse_stock.REMARK
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.DELETE_FLAG
     *
     * @return the value of tb_warehouse_stock.DELETE_FLAG
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.DELETE_FLAG
     *
     * @param deleteFlag the value for tb_warehouse_stock.DELETE_FLAG
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.CREATE_EMP
     *
     * @return the value of tb_warehouse_stock.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.CREATE_EMP
     *
     * @param createEmp the value for tb_warehouse_stock.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.CREATE_DATE
     *
     * @return the value of tb_warehouse_stock.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.CREATE_DATE
     *
     * @param createDate the value for tb_warehouse_stock.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.UPDATE_EMP
     *
     * @return the value of tb_warehouse_stock.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.UPDATE_EMP
     *
     * @param updateEmp the value for tb_warehouse_stock.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.UPDATE_DATE
     *
     * @return the value of tb_warehouse_stock.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.UPDATE_DATE
     *
     * @param updateDate the value for tb_warehouse_stock.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_stock.VERSION
     *
     * @return the value of tb_warehouse_stock.VERSION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_stock.VERSION
     *
     * @param version the value for tb_warehouse_stock.VERSION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}