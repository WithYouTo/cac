package com.qcap.cac.entity;

import java.io.Serializable;
import java.util.Date;

public class Tbpubcode01 implements Serializable{
    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 4938535279088378385L;

	/**
    *   主键
     */
    private String tbpubcode01Id;

    /**
    *   小区Id
     */
    private String areaId;

    /**
    *   公司别
     */
    private String companyCode;

    /**
    *   逻辑外键
     */
    private String tbpubcodeId;

    /**
    *   配置项目代码
     */
    private String configCode;

    /**
    *   顺序号
     */
    private String seq;

    /**
    *   描述
     */
    private String desc;

    /**
    *   描述1
     */
    private String desc1;

    /**
    *   描述2
     */
    private String desc2;

    /**
    *   描述3
     */
    private String desc3;

    /**
    *   描述4
     */
    private String desc4;

    /**
    *   描述5
     */
    private String desc5;

    /**
    *   描述6
     */
    private String desc6;

    /**
    *   描述7
     */
    private String desc7;

    /**
    *   描述8
     */
    private String desc8;

    /**
    *   描述9
     */
    private String desc9;

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
     * This method returns the value of the database column tbpubcode01.TBPUBCODE01_ID_
     *
     * @return the value of tbpubcode01.TBPUBCODE01_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getTbpubcode01Id() {
        return tbpubcode01Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.TBPUBCODE01_ID_
     *
     * @param tbpubcode01Id the value for tbpubcode01.TBPUBCODE01_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setTbpubcode01Id(String tbpubcode01Id) {
        this.tbpubcode01Id = tbpubcode01Id == null ? null : tbpubcode01Id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.AREA_ID_
     *
     * @return the value of tbpubcode01.AREA_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.AREA_ID_
     *
     * @param areaId the value for tbpubcode01.AREA_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.COMPANY_CODE_
     *
     * @return the value of tbpubcode01.COMPANY_CODE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.COMPANY_CODE_
     *
     * @param companyCode the value for tbpubcode01.COMPANY_CODE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode == null ? null : companyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.TBPUBCODE_ID_
     *
     * @return the value of tbpubcode01.TBPUBCODE_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getTbpubcodeId() {
        return tbpubcodeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.TBPUBCODE_ID_
     *
     * @param tbpubcodeId the value for tbpubcode01.TBPUBCODE_ID_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setTbpubcodeId(String tbpubcodeId) {
        this.tbpubcodeId = tbpubcodeId == null ? null : tbpubcodeId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.CONFIG_CODE_
     *
     * @return the value of tbpubcode01.CONFIG_CODE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getConfigCode() {
        return configCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.CONFIG_CODE_
     *
     * @param configCode the value for tbpubcode01.CONFIG_CODE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.SEQ_
     *
     * @return the value of tbpubcode01.SEQ_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getSeq() {
        return seq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.SEQ_
     *
     * @param seq the value for tbpubcode01.SEQ_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setSeq(String seq) {
        this.seq = seq == null ? null : seq.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC_
     *
     * @return the value of tbpubcode01.DESC_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc() {
        return desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC_
     *
     * @param desc the value for tbpubcode01.DESC_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC1_
     *
     * @return the value of tbpubcode01.DESC1_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc1() {
        return desc1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC1_
     *
     * @param desc1 the value for tbpubcode01.DESC1_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc1(String desc1) {
        this.desc1 = desc1 == null ? null : desc1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC2_
     *
     * @return the value of tbpubcode01.DESC2_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc2() {
        return desc2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC2_
     *
     * @param desc2 the value for tbpubcode01.DESC2_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc2(String desc2) {
        this.desc2 = desc2 == null ? null : desc2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC3_
     *
     * @return the value of tbpubcode01.DESC3_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc3() {
        return desc3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC3_
     *
     * @param desc3 the value for tbpubcode01.DESC3_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc3(String desc3) {
        this.desc3 = desc3 == null ? null : desc3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC4_
     *
     * @return the value of tbpubcode01.DESC4_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc4() {
        return desc4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC4_
     *
     * @param desc4 the value for tbpubcode01.DESC4_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc4(String desc4) {
        this.desc4 = desc4 == null ? null : desc4.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC5_
     *
     * @return the value of tbpubcode01.DESC5_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc5() {
        return desc5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC5_
     *
     * @param desc5 the value for tbpubcode01.DESC5_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc5(String desc5) {
        this.desc5 = desc5 == null ? null : desc5.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC6_
     *
     * @return the value of tbpubcode01.DESC6_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc6() {
        return desc6;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC6_
     *
     * @param desc6 the value for tbpubcode01.DESC6_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc6(String desc6) {
        this.desc6 = desc6 == null ? null : desc6.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC7_
     *
     * @return the value of tbpubcode01.DESC7_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc7() {
        return desc7;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC7_
     *
     * @param desc7 the value for tbpubcode01.DESC7_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc7(String desc7) {
        this.desc7 = desc7 == null ? null : desc7.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC8_
     *
     * @return the value of tbpubcode01.DESC8_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc8() {
        return desc8;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC8_
     *
     * @param desc8 the value for tbpubcode01.DESC8_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc8(String desc8) {
        this.desc8 = desc8 == null ? null : desc8.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.DESC9_
     *
     * @return the value of tbpubcode01.DESC9_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getDesc9() {
        return desc9;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.DESC9_
     *
     * @param desc9 the value for tbpubcode01.DESC9_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setDesc9(String desc9) {
        this.desc9 = desc9 == null ? null : desc9.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.CREATE_EMP_
     *
     * @return the value of tbpubcode01.CREATE_EMP_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.CREATE_EMP_
     *
     * @param createEmp the value for tbpubcode01.CREATE_EMP_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.CREATE_DATE_
     *
     * @return the value of tbpubcode01.CREATE_DATE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.CREATE_DATE_
     *
     * @param createDate the value for tbpubcode01.CREATE_DATE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.UPDATE_DATE_
     *
     * @return the value of tbpubcode01.UPDATE_DATE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.UPDATE_DATE_
     *
     * @param updateDate the value for tbpubcode01.UPDATE_DATE_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.UPDATE_EMP_
     *
     * @return the value of tbpubcode01.UPDATE_EMP_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.UPDATE_EMP_
     *
     * @param updateEmp the value for tbpubcode01.UPDATE_EMP_
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbpubcode01.VERSION
     *
     * @return the value of tbpubcode01.VERSION
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbpubcode01.VERSION
     *
     * @param version the value for tbpubcode01.VERSION
     *
     * @mbggenerated Tue Apr 10 17:26:01 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}