package com.qcap.cac.dto;

public class GoodsReqRestReq {

	/**
	 * 领用申请主键ID
	 */
	private String warehouseRequId;

	/**
	 *   储藏室主键
	 */
	private String storeroomId;

	/**
	 *   储藏室名称
	 */
	private String storeroom;

	/**
	 *   领用批次号生成规则（P +YYMMDDHHMMSS）
	 */
	private String requBatchNo;

	/**
	 *   领用人ID
	 */
	private String requPerson;

	/**
	 *   领用人电话
	 */
	private String requMobile;

	/**
	 *   领用人姓名
	 */
	private String requName;

	/**
	 *   领用日期
	 */
	private String requTime;

	/**
	 *   岗位Id
	 */
	private String positionCode;

    /**
     *   岗位名称
     */
    private String positionName;


	/**
	 *   领用事由
	 */
	private String requRemark;

	/**
	 *   INIT已申请，COMMIT已提交，RECEIVE已领用
	 */
	private String requStatus;

	/**
	 *   新增时间
	 */
	private String createDate;

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getWarehouseRequId() {
		return warehouseRequId;
	}

	public void setWarehouseRequId(String warehouseRequId) {
		this.warehouseRequId = warehouseRequId;
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

	public String getRequBatchNo() {
		return requBatchNo;
	}

	public void setRequBatchNo(String requBatchNo) {
		this.requBatchNo = requBatchNo;
	}

	public String getRequPerson() {
		return requPerson;
	}

	public void setRequPerson(String requPerson) {
		this.requPerson = requPerson;
	}

	public String getRequMobile() {
		return requMobile;
	}

	public void setRequMobile(String requMobile) {
		this.requMobile = requMobile;
	}

	public String getRequName() {
		return requName;
	}

	public void setRequName(String requName) {
		this.requName = requName;
	}

	public String getRequTime() {
		return requTime;
	}

	public void setRequTime(String requTime) {
		this.requTime = requTime;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getRequRemark() {
		return requRemark;
	}

	public void setRequRemark(String requRemark) {
		this.requRemark = requRemark;
	}

	public String getRequStatus() {
		return requStatus;
	}

	public void setRequStatus(String requStatus) {
		this.requStatus = requStatus;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
