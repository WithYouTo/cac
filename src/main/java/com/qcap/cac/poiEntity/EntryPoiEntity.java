package com.qcap.cac.poiEntity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: zengxin
 * @Date: 2018/10/10 14:01
 * @Description:
 */
public class EntryPoiEntity implements Serializable {

    private static final long serialVersionUID = -6466629959268081444L;

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

    @Excel(name = "储藏室名称", orderNum = "6")
    private String storeRoom;

    @Excel(name = "建筑物", orderNum = "7")
    private String building;

    @Excel(name = "楼层", orderNum = "8")
    private String floor;

    @Excel(name = "室", orderNum = "9")
    private String room;

    @Excel(name = "数量", orderNum = "10")
    private String entryNum;

    @Excel(name = "单位",orderNum = "11")
    private String entryUnit;

    @Excel(name = "最低警戒线",orderNum = "12")
    private String limitStore;

    @Excel(name = "最低警戒线单位",orderNum = "13")
    private String limitStoreUnit;

    @Excel(name = "总数量",orderNum = "14")
    private String sumNum;

    @Excel(name = "最小单位",orderNum = "15")
    private String minUnit;

    @Excel(name = "采购数量",orderNum = "16")
    private String buyNum;

    @Excel(name = "采购单位",orderNum = "17")
    private String buyUnit;

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

    public String getStoreRoom() {
        return storeRoom;
    }

    public void setStoreRoom(String storeRoom) {
        this.storeRoom = storeRoom;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(String entryNum) {
        this.entryNum = entryNum;
    }

    public String getEntryUnit() {
        return entryUnit;
    }

    public void setEntryUnit(String entryUnit) {
        this.entryUnit = entryUnit;
    }

    public String getLimitStore() {
        return limitStore;
    }

    public void setLimitStore(String limitStore) {
        this.limitStore = limitStore;
    }

    public String getLimitStoreUnit() {
        return limitStoreUnit;
    }

    public void setLimitStoreUnit(String limitStoreUnit) {
        this.limitStoreUnit = limitStoreUnit;
    }

    public String getSumNum() {
        return sumNum;
    }

    public void setSumNum(String sumNum) {
        this.sumNum = sumNum;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public String getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(String buyNum) {
        this.buyNum = buyNum;
    }

    public String getBuyUnit() {
        return buyUnit;
    }

    public void setBuyUnit(String buyUnit) {
        this.buyUnit = buyUnit;
    }

    @Override
    public String toString() {
        return "EntryPoiEntity{" +
                "buyNo='" + buyNo + '\'' +
                ", buyType='" + buyType + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", storeRoom='" + storeRoom + '\'' +
                ", building='" + building + '\'' +
                ", floor='" + floor + '\'' +
                ", room='" + room + '\'' +
                ", entryNum='" + entryNum + '\'' +
                ", entryUnit='" + entryUnit + '\'' +
                ", limitStore='" + limitStore + '\'' +
                ", limitStoreUnit='" + limitStoreUnit + '\'' +
                ", sumNum='" + sumNum + '\'' +
                ", minUnit='" + minUnit + '\'' +
                ", buyNum='" + buyNum + '\'' +
                ", buyUnit='" + buyUnit + '\'' +
                '}';
    }
}
