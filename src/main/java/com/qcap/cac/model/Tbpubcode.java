package com.qcap.cac.model;

import java.io.Serializable;
import java.util.Date;

public class Tbpubcode implements Serializable{
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -3684945515356910203L;

	/**
    *   主键
     */
    private String tbpubcodeId;

    /**
    *   小区Id
     */
    private String areaId;

    /**
    *   公司别
     */
    private String companyCode;

    /**
    *   配置项目代码
     */
    private String configCode;

    /**
    *   配置项目名称
     */
    private String configName;

    /**
    *   配置项目层级类型，0单层，1多层
     */
    private String layerType;

    /**
    *   删除标示：0未删除，1已删除
     */
    private String delFlag;

    /**
    *   数值
     */
    private String configValue;

    /**
    *   备注
     */
    private String notice;

    /**
    *   备注1
     */
    private String remark1;

    /**
    *   备注2
     */
    private String remark2;

    /**
    *   备注3
     */
    private String remark3;

    /**
    *   新增人
     */
    private String createEmp;

    /**
    *   新增时间
     */
    private Date createDate;

    /**
    *   修改时间
     */
    private Date updateDate;

    /**
    *   修改人
     */
    private String updateEmp;

    /**
    *   版本
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.TBPUBCODE_ID_
     *
     * @return the value of tbpubcode.TBPUBCODE_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getTbpubcodeId() {
        return tbpubcodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.TBPUBCODE_ID_
     *
     * @param tbpubcodeId the value for tbpubcode.TBPUBCODE_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setTbpubcodeId(String tbpubcodeId) {
        this.tbpubcodeId = tbpubcodeId == null ? null : tbpubcodeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.AREA_ID_
     *
     * @return the value of tbpubcode.AREA_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.AREA_ID_
     *
     * @param areaId the value for tbpubcode.AREA_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.COMPANY_CODE_
     *
     * @return the value of tbpubcode.COMPANY_CODE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.COMPANY_CODE_
     *
     * @param companyCode the value for tbpubcode.COMPANY_CODE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.CONFIG_CODE_
     *
     * @return the value of tbpubcode.CONFIG_CODE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getConfigCode() {
        return configCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.CONFIG_CODE_
     *
     * @param configCode the value for tbpubcode.CONFIG_CODE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.CONFIG_NAME_
     *
     * @return the value of tbpubcode.CONFIG_NAME_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getConfigName() {
        return configName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.CONFIG_NAME_
     *
     * @param configName the value for tbpubcode.CONFIG_NAME_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.LAYER_TYPE_
     *
     * @return the value of tbpubcode.LAYER_TYPE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getLayerType() {
        return layerType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.LAYER_TYPE_
     *
     * @param layerType the value for tbpubcode.LAYER_TYPE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setLayerType(String layerType) {
        this.layerType = layerType == null ? null : layerType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.DEL_FLAG_
     *
     * @return the value of tbpubcode.DEL_FLAG_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.DEL_FLAG_
     *
     * @param delFlag the value for tbpubcode.DEL_FLAG_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.CONFIG_VALUE_
     *
     * @return the value of tbpubcode.CONFIG_VALUE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getConfigValue() {
        return configValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.CONFIG_VALUE_
     *
     * @param configValue the value for tbpubcode.CONFIG_VALUE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.NOTICE_
     *
     * @return the value of tbpubcode.NOTICE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getNotice() {
        return notice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.NOTICE_
     *
     * @param notice the value for tbpubcode.NOTICE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.REMARK1_
     *
     * @return the value of tbpubcode.REMARK1_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.REMARK1_
     *
     * @param remark1 the value for tbpubcode.REMARK1_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.REMARK2_
     *
     * @return the value of tbpubcode.REMARK2_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.REMARK2_
     *
     * @param remark2 the value for tbpubcode.REMARK2_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.REMARK3_
     *
     * @return the value of tbpubcode.REMARK3_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.REMARK3_
     *
     * @param remark3 the value for tbpubcode.REMARK3_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.CREATE_EMP_
     *
     * @return the value of tbpubcode.CREATE_EMP_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.CREATE_EMP_
     *
     * @param createEmp the value for tbpubcode.CREATE_EMP_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.CREATE_DATE_
     *
     * @return the value of tbpubcode.CREATE_DATE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.CREATE_DATE_
     *
     * @param createDate the value for tbpubcode.CREATE_DATE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.UPDATE_DATE_
     *
     * @return the value of tbpubcode.UPDATE_DATE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.UPDATE_DATE_
     *
     * @param updateDate the value for tbpubcode.UPDATE_DATE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.UPDATE_EMP_
     *
     * @return the value of tbpubcode.UPDATE_EMP_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.UPDATE_EMP_
     *
     * @param updateEmp the value for tbpubcode.UPDATE_EMP_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode.VERSION
     *
     * @return the value of tbpubcode.VERSION
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode.VERSION
     *
     * @param version the value for tbpubcode.VERSION
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}