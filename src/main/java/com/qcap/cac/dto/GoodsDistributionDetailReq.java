package com.qcap.cac.dto;

public class GoodsDistributionDetailReq {

	/**
	 * 关联主键
	 */
	private String ids;

	/**
	 *  物品编码
	 */
	private String goodsNo;

	/**
	 *   物品名称
	 */
	private String goodsName;

	/**
	 * 可发放数量
	 */
	private Integer availNum;

	/**
	 * 单位
	 */
	private String minUnit;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public Integer getAvailNum() {
		return availNum;
	}

	public void setAvailNum(Integer availNum) {
		this.availNum = availNum;
	}

	public String getMinUnit() {
		return minUnit;
	}

	public void setMinUnit(String minUnit) {
		this.minUnit = minUnit;
	}
}
