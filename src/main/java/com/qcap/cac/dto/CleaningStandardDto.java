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

/** 
 * 
 * @ClassName: CleaningStandardDto 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:23:24  
 */
public class CleaningStandardDto {
	
	private String standardCode;
	
    public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

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

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getStandardStep() {
		return standardStep;
	}

	public void setStandardStep(String standardStep) {
		this.standardStep = standardStep;
	}

	public String getEquitmetType() {
		return equitmetType;
	}

	public void setEquitmetType(String equitmetType) {
		this.equitmetType = equitmetType;
	}

	public String getCleanTools() {
		return cleanTools;
	}

	public void setCleanTools(String cleanTools) {
		this.cleanTools = cleanTools;
	}

	public String getStandardRequirement() {
		return standardRequirement;
	}

	public void setStandardRequirement(String standardRequirement) {
		this.standardRequirement = standardRequirement;
	}

	public String getMarkScale() {
		return markScale;
	}

	public void setMarkScale(String markScale) {
		this.markScale = markScale;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getUploadPicFlag() {
		return uploadPicFlag;
	}

	public void setUploadPicFlag(String uploadPicFlag) {
		this.uploadPicFlag = uploadPicFlag;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	@Override
	public String toString() {
		return "CleaningStandardDto [standardCode=" + standardCode + ", standardName=" + standardName
				+ ", standardStep=" + standardStep + ", equitmetType=" + equitmetType + ", cleanTools=" + cleanTools
				+ ", standardRequirement=" + standardRequirement + ", markScale=" + markScale + ", checkFlag="
				+ checkFlag + ", uploadPicFlag=" + uploadPicFlag + ", remark1=" + remark1 + ", remark2=" + remark2
				+ ", remark3=" + remark3 + "]";
	}


    
}
