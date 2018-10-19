package com.qcap.cac.poiEntity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * 请购单实体类
 * @Auther: zengxin
 * @Date: 2018/10/10 14:01
 * @Description:
 */
public class PurchasePoiEntity implements Serializable {

    private static final long serialVersionUID = -6466629959268081444L;

    @Excel(name = "序号", orderNum = "0")
    private String seqNo;


    @Excel(name = "采购编号", orderNum = "1")
    private String buyNo;

    @Excel(name = "采购类别", orderNum = "2")
    private String buyType;

    @Excel(name = "物品类别", orderNum = "3")
    private String goodsType;

    @Excel(name = "物品名称", orderNum = "4")
    private String goodsName;

    @Excel(name = "供应商名称", orderNum = "5")
    private String supplierName;

    @Excel(name = "采购数量", orderNum = "6")
    private String buyNum;

    @Excel(name = "采购单位",orderNum = "7")
    private String entryUnit;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBuyNo() {
        return buyNo;
    }

    public void setBuyNo(String buyNo) {
        this.buyNo = buyNo;
    }

    public String getBuyType() {
        return buyType;
    }

    public void setBuyType(String buyType) {
        this.buyType = buyType;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }
}
