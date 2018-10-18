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
 * @ClassName: CleaningStandardDetailDto 
 * @author: 夏胜专
 * @date: 2018年10月14日 上午11:23:24  
 */
public class CleaningStandardDetailDto {
	private String standardDetailId;
	private String standardCode;

    private String material;

    private String standardStep;

    private String equitmetType;

    private String cleanTools;

    private String standardRequirement;

    private String imgUrl;

    private String fileUrl;

    private String remark1;

    private String remark2;

    private String remark3;
    

	public String getStandardDetailId() {
		return standardDetailId;
	}

	public void setStandardDetailId(String standardDetailId) {
		this.standardDetailId = standardDetailId;
	}

	public String getStandardCode() {
		return standardCode;
	}

	public void setStandardCode(String standardCode) {
		this.standardCode = standardCode;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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
		return "CleaningStandardDetailDto [standardCode=" + standardCode + ", material=" + material + ", standardStep="
				+ standardStep + ", equitmetType=" + equitmetType + ", cleanTools=" + cleanTools
				+ ", standardRequirement=" + standardRequirement + ", imgUrl=" + imgUrl + ", fileUrl=" + fileUrl
				+ ", remark1=" + remark1 + ", remark2=" + remark2 + ", remark3=" + remark3 + "]";
	}
    
    
    
}
