/**   
 * Copyright © 2018 公司名. All rights reserved.
 * 
 * @Title: CleaningStandardDto.java 
 * @Prject: cac
 * @Package: com.qcap.cac.dto 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:23:24 
 * @version: V1.0   
 */
package com.qcap.cac.dto;

import java.util.Date;

/** 
 * 
 * @ClassName: CleaningStandardDto 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:23:24  
 */
public class CleaningStandardDto {
	
	
    private String standardId;

    private String standardCode;

    private String standardName;

    private String standardStep;

    private String equitmetType;

    private String cleanTools;

    private String standardRequirement;

    private String markScale;

    private String checkFlag;

    private String uploadPicFlag;

    private String remark1;

    private String remark2;

    private String remark3;

    private String createEmp;

    private Date createDate;

    private Date updateDate;

    private String updateEmp;

    private Integer version;

    public String getstandardId() {
        return standardId;
    }

    public void setstandardId(String standardId) {
        this.standardId = standardId == null ? null : standardId.trim();
    }

    public String getStandardCode() {
        return standardCode;
    }

    public void setStandardCode(String standardCode) {
        this.standardCode = standardCode == null ? null : standardCode.trim();
    }

    public String getStandardName() {
        return standardName;
    }

    public void setStandardName(String standardName) {
        this.standardName = standardName == null ? null : standardName.trim();
    }

    public String getStandardStep() {
        return standardStep;
    }

    public void setStandardStep(String standardStep) {
        this.standardStep = standardStep == null ? null : standardStep.trim();
    }

    public String getEquitmetType() {
        return equitmetType;
    }

    public void setEquitmetType(String equitmetType) {
        this.equitmetType = equitmetType == null ? null : equitmetType.trim();
    }

    public String getCleanTools() {
        return cleanTools;
    }

    public void setCleanTools(String cleanTools) {
        this.cleanTools = cleanTools == null ? null : cleanTools.trim();
    }

    public String getStandardRequirement() {
        return standardRequirement;
    }

    public void setStandardRequirement(String standardRequirement) {
        this.standardRequirement = standardRequirement == null ? null : standardRequirement.trim();
    }

    public String getMarkScale() {
        return markScale;
    }

    public void setMarkScale(String markScale) {
        this.markScale = markScale == null ? null : markScale.trim();
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag == null ? null : checkFlag.trim();
    }

    public String getUploadPicFlag() {
        return uploadPicFlag;
    }

    public void setUploadPicFlag(String uploadPicFlag) {
        this.uploadPicFlag = uploadPicFlag == null ? null : uploadPicFlag.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateEmp() {
        return updateEmp;
    }

    @Override
	public String toString() {
		return "CleaningStandardDto [standardId=" + standardId + ", standardCode=" + standardCode
				+ ", standardName=" + standardName + ", standardStep=" + standardStep + ", equitmetType="
				+ equitmetType + ", cleanTools=" + cleanTools + ", standardRequirement=" + standardRequirement
				+ ", markScale=" + markScale + ", checkFlag=" + checkFlag + ", uploadPicFlag=" + uploadPicFlag
				+ ", remark1=" + remark1 + ", remark2=" + remark2 + ", remark3=" + remark3 + ", createEmp="
				+ createEmp + ", createDate=" + createDate + ", updateDate=" + updateDate + ", updateEmp="
				+ updateEmp + ", version=" + version + "]";
	}

	public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
