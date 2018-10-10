package com.qcap.cac.entity;

import java.util.Date;

public class TbWarehouseRequ {
    /**
     *   领用申请主键ID
     */
    private String warehouseRequId;

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
    private Date requTime;

    /**
     *   领用事由
     */
    private String requRemark;

    /**
     *   INIT已申请，COMMIT已提交，RECEIVE已领用
     */
    private String requStatus;

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
     * This method returns the value of the database column tb_warehouse_requ.WAREHOUSE_REQU_ID
     *
     * @return the value of tb_warehouse_requ.WAREHOUSE_REQU_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getWarehouseRequId() {
        return warehouseRequId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.WAREHOUSE_REQU_ID
     *
     * @param warehouseRequId the value for tb_warehouse_requ.WAREHOUSE_REQU_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setWarehouseRequId(String warehouseRequId) {
        this.warehouseRequId = warehouseRequId == null ? null : warehouseRequId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.REQU_BATCH_NO
     *
     * @return the value of tb_warehouse_requ.REQU_BATCH_NO
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRequBatchNo() {
        return requBatchNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.REQU_BATCH_NO
     *
     * @param requBatchNo the value for tb_warehouse_requ.REQU_BATCH_NO
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRequBatchNo(String requBatchNo) {
        this.requBatchNo = requBatchNo == null ? null : requBatchNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.REQU_PERSON
     *
     * @return the value of tb_warehouse_requ.REQU_PERSON
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRequPerson() {
        return requPerson;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.REQU_PERSON
     *
     * @param requPerson the value for tb_warehouse_requ.REQU_PERSON
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRequPerson(String requPerson) {
        this.requPerson = requPerson == null ? null : requPerson.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.REQU_MOBILE
     *
     * @return the value of tb_warehouse_requ.REQU_MOBILE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRequMobile() {
        return requMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.REQU_MOBILE
     *
     * @param requMobile the value for tb_warehouse_requ.REQU_MOBILE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRequMobile(String requMobile) {
        this.requMobile = requMobile == null ? null : requMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.REQU_NAME
     *
     * @return the value of tb_warehouse_requ.REQU_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRequName() {
        return requName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.REQU_NAME
     *
     * @param requName the value for tb_warehouse_requ.REQU_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRequName(String requName) {
        this.requName = requName == null ? null : requName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.REQU_TIME
     *
     * @return the value of tb_warehouse_requ.REQU_TIME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Date getRequTime() {
        return requTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.REQU_TIME
     *
     * @param requTime the value for tb_warehouse_requ.REQU_TIME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRequTime(Date requTime) {
        this.requTime = requTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.REQU_REMARK
     *
     * @return the value of tb_warehouse_requ.REQU_REMARK
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRequRemark() {
        return requRemark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.REQU_REMARK
     *
     * @param requRemark the value for tb_warehouse_requ.REQU_REMARK
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRequRemark(String requRemark) {
        this.requRemark = requRemark == null ? null : requRemark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.REQU_STATUS
     *
     * @return the value of tb_warehouse_requ.REQU_STATUS
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRequStatus() {
        return requStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.REQU_STATUS
     *
     * @param requStatus the value for tb_warehouse_requ.REQU_STATUS
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRequStatus(String requStatus) {
        this.requStatus = requStatus == null ? null : requStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.CREATE_EMP
     *
     * @return the value of tb_warehouse_requ.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.CREATE_EMP
     *
     * @param createEmp the value for tb_warehouse_requ.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.CREATE_DATE
     *
     * @return the value of tb_warehouse_requ.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.CREATE_DATE
     *
     * @param createDate the value for tb_warehouse_requ.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.UPDATE_EMP
     *
     * @return the value of tb_warehouse_requ.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.UPDATE_EMP
     *
     * @param updateEmp the value for tb_warehouse_requ.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.UPDATE_DATE
     *
     * @return the value of tb_warehouse_requ.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.UPDATE_DATE
     *
     * @param updateDate the value for tb_warehouse_requ.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_requ.VERSION
     *
     * @return the value of tb_warehouse_requ.VERSION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_requ.VERSION
     *
     * @param version the value for tb_warehouse_requ.VERSION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}