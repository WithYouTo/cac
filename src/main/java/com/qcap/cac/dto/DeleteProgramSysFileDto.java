package com.qcap.cac.dto;

public class DeleteProgramSysFileDto {
	
	private String fileId;
	
	private String programId;
	
	private String contractFile;

	public String getFileId() {
		return fileId;
	}

	public String getProgramId() {
		return programId;
	}

	public String getContractFile() {
		return contractFile;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public void setContractFile(String contractFile) {
		this.contractFile = contractFile;
	}
	
	
}
