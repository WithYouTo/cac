package com.qcap.cac.dto;

public class GoodsReqDetailReq {

    /**
     * 领用明细主键ID
     */
    private String warehouseReqDetailId;


    /**
     * 领用申请主键ID
     */
    private String warehouseRequId;

    /**
     *  物品编码
     */
    private String goodsNo;

    /**
     *   物品名称
     */
    private String goodsName;

    /**
     * 申请数量
     */
    private Integer applyNum;

    /**
     * 单位
     */
    private String minUnit;


    /**
     * 库存数量
     */
    private Integer goodsNum;


    /**
     *   领用事由
     */
    private String requRemark;

    /**
     *   分页序号
     */
    private String lineNo;

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getWarehouseReqDetailId() {
        return warehouseReqDetailId;
    }

    public void setWarehouseReqDetailId(String warehouseReqDetailId) {
        this.warehouseReqDetailId = warehouseReqDetailId;
    }

    public String getWarehouseRequId() {
        return warehouseRequId;
    }

    public void setWarehouseRequId(String warehouseRequId) {
        this.warehouseRequId = warehouseRequId;
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

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getMinUnit() {
        return minUnit;
    }

    public void setMinUnit(String minUnit) {
        this.minUnit = minUnit;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getRequRemark() {
        return requRemark;
    }

    public void setRequRemark(String requRemark) {
        this.requRemark = requRemark;
    }

    @Override
    public String toString() {
        return "GoodsReqDetailReq{" +
                "warehouseReqDetailId='" + warehouseReqDetailId + '\'' +
                ", warehouseRequId='" + warehouseRequId + '\'' +
                ", goodsNo='" + goodsNo + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", applyNum=" + applyNum +
                ", minUnit='" + minUnit + '\'' +
                ", goodsNum=" + goodsNum +
                ", requRemark='" + requRemark + '\'' +
                '}';
    }

}
