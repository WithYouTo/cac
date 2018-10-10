package com.qcap.cac.entity;

import java.util.Date;

public class TbAreaStandard {
    /**
    *   主键
     */
    private String standaedId;

    /**
    *   标准编码
     */
    private String standardCode;

    /**
    *   标准名称
     */
    private String standardName;

    /**
    *   步骤
     */
    private String standardStep;

    /**
    *   设备类型
     */
    private String equitmetType;

    /**
    *   用具
     */
    private String cleanTools;

    /**
    *   标准清洁要求
     */
    private String standardRequirement;

    /**
    *   评分标准
     */
    private String markScale;

    /**
    *   是否必须检查
     */
    private String checkFlag;

    /**
    *   任务是否必须上传图片
     */
    private String uploadPicFlag;

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
     * This method returns the value of the database column tb_area_standard.STANDAED_ID
     *
     * @return the value of tb_area_standard.STANDAED_ID
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getStandaedId() {
        return standaedId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.STANDAED_ID
     *
     * @param standaedId the value for tb_area_standard.STANDAED_ID
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setStandaedId(String standaedId) {
        this.standaedId = standaedId == null ? null : standaedId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.STANDARD_CODE
     *
     * @return the value of tb_area_standard.STANDARD_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getStandardCode() {
        return standardCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.STANDARD_CODE
     *
     * @param standardCode the value for tb_area_standard.STANDARD_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode == null ? null : standardCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.STANDARD_NAME
     *
     * @return the value of tb_area_standard.STANDARD_NAME
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getStandardName() {
        return standardName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.STANDARD_NAME
     *
     * @param standardName the value for tb_area_standard.STANDARD_NAME
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setStandardName(String standardName) {
        this.standardName = standardName == null ? null : standardName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.STANDARD_STEP
     *
     * @return the value of tb_area_standard.STANDARD_STEP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getStandardStep() {
        return standardStep;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.STANDARD_STEP
     *
     * @param standardStep the value for tb_area_standard.STANDARD_STEP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setStandardStep(String standardStep) {
        this.standardStep = standardStep == null ? null : standardStep.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.EQUITMET_TYPE
     *
     * @return the value of tb_area_standard.EQUITMET_TYPE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getEquitmetType() {
        return equitmetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.EQUITMET_TYPE
     *
     * @param equitmetType the value for tb_area_standard.EQUITMET_TYPE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setEquitmetType(String equitmetType) {
        this.equitmetType = equitmetType == null ? null : equitmetType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.CLEAN_TOOLS
     *
     * @return the value of tb_area_standard.CLEAN_TOOLS
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getCleanTools() {
        return cleanTools;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.CLEAN_TOOLS
     *
     * @param cleanTools the value for tb_area_standard.CLEAN_TOOLS
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setCleanTools(String cleanTools) {
        this.cleanTools = cleanTools == null ? null : cleanTools.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.STANDARD_REQUIREMENT
     *
     * @return the value of tb_area_standard.STANDARD_REQUIREMENT
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getStandardRequirement() {
        return standardRequirement;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.STANDARD_REQUIREMENT
     *
     * @param standardRequirement the value for tb_area_standard.STANDARD_REQUIREMENT
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setStandardRequirement(String standardRequirement) {
        this.standardRequirement = standardRequirement == null ? null : standardRequirement.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.MARK_SCALE
     *
     * @return the value of tb_area_standard.MARK_SCALE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getMarkScale() {
        return markScale;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.MARK_SCALE
     *
     * @param markScale the value for tb_area_standard.MARK_SCALE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setMarkScale(String markScale) {
        this.markScale = markScale == null ? null : markScale.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.CHECK_FLAG
     *
     * @return the value of tb_area_standard.CHECK_FLAG
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getCheckFlag() {
        return checkFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.CHECK_FLAG
     *
     * @param checkFlag the value for tb_area_standard.CHECK_FLAG
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag == null ? null : checkFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.UPLOAD_PIC_FLAG
     *
     * @return the value of tb_area_standard.UPLOAD_PIC_FLAG
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getUploadPicFlag() {
        return uploadPicFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.UPLOAD_PIC_FLAG
     *
     * @param uploadPicFlag the value for tb_area_standard.UPLOAD_PIC_FLAG
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setUploadPicFlag(String uploadPicFlag) {
        this.uploadPicFlag = uploadPicFlag == null ? null : uploadPicFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.REMARK1
     *
     * @return the value of tb_area_standard.REMARK1
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.REMARK1
     *
     * @param remark1 the value for tb_area_standard.REMARK1
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.REMARK2
     *
     * @return the value of tb_area_standard.REMARK2
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.REMARK2
     *
     * @param remark2 the value for tb_area_standard.REMARK2
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.REMARK3
     *
     * @return the value of tb_area_standard.REMARK3
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.REMARK3
     *
     * @param remark3 the value for tb_area_standard.REMARK3
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.CREATE_EMP
     *
     * @return the value of tb_area_standard.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.CREATE_EMP
     *
     * @param createEmp the value for tb_area_standard.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.CREATE_DATE
     *
     * @return the value of tb_area_standard.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.CREATE_DATE
     *
     * @param createDate the value for tb_area_standard.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.UPDATE_DATE
     *
     * @return the value of tb_area_standard.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.UPDATE_DATE
     *
     * @param updateDate the value for tb_area_standard.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.UPDATE_EMP
     *
     * @return the value of tb_area_standard.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.UPDATE_EMP
     *
     * @param updateEmp the value for tb_area_standard.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area_standard.VERSION
     *
     * @return the value of tb_area_standard.VERSION
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area_standard.VERSION
     *
     * @param version the value for tb_area_standard.VERSION
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}