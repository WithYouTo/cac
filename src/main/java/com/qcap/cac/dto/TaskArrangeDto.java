package com.qcap.cac.dto;

public class TaskArrangeDto {
	
	private String shift;
	
	private String month;
	
	private String createEmp;

	public String getShift() {
		return shift;
	}

	public String getMonth() {
		return month;
	}

	public String getCreateEmp() {
		return createEmp;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public void setCreateEmp(String createEmp) {
		this.createEmp = createEmp;
	}

	@Override
	public String toString() {
		return "TaskArrangeSearchDto [shift=" + shift + ", month=" + month + ", createEmp=" + createEmp + "]";
	}
	
	
}
