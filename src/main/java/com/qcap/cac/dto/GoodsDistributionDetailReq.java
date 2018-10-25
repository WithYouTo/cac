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
	 *  岗位编码
	 */
	private String positionCode;

	/**
	 *   岗位名称
	 */
	private String positionName;

	/**
	 * 可发放数量
	 */
	private Integer availNum;

	/**
	 * 初始化数量
	 */
	private Integer distrNum;

	/**
	 * 单位
	 */
	private String minUnit;

	/**
	 *   分页序号
	 */
	private String lineNo;

	public Integer getDistrNum() {
		return distrNum;
	}

	public void setDistrNum(Integer distrNum) {
		this.distrNum = distrNum;
	}

	public String getLineNo() {
		return lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

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

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
}
