package com.qcap.cac.dto;

import java.math.BigDecimal;

public class ProgramAddDto {
	
		private String programId;
	
	    private String programCode;

	    /**
	    *   项目名称
	     */
	    private String programName;

	    /**
	    *   建筑面积
	     */
	    private BigDecimal coveredArea;

	    /**
	    *   业态
	     */
	    private String programStatus;

	    /**
	    *   项目地址
	     */
	    private String programAddress;

	    /**
	    *   承接方式
	     */
	    private String acceptMode;

	    /**
	    *   管理面积
	     */
	    private BigDecimal managementArea;

	    /**
	    *   建筑结构
	     */
	    private String buildingStructure;

	    /**
	    *   合同金额
	     */
	    private BigDecimal contractMoney;

	    /**
	    *   合同有效期
	     */
	    private String contractDuration;

	    /**
	    *   合同约定人数
	     */
	    private String promisedNum;

	    /**
	    *   合同约定最低人数
	     */
	    private String minNum;

	    /**
	    *   实际投入人数
	     */
	    private String realNum;

	    /**
	    *   办公室数量
	     */
	    private String officeRoomNum;

	    /**
	    *   会议室数量
	     */
	    private String meetingRoomNum;

	    /**
	    *   卫生间数量
	     */
	    private String washroomNum;

	    /**
	    *   母婴室数量
	     */
	    private String babyCareRoomNum;

	    /**
	    *   吸烟室数量
	     */
	    private String smokingRoomNum;

	    /**
	    *   饮水机/饮水站数量
	     */
	    private String drinkingFountainNum;

	    /**
	    *   电梯数量
	     */
	    private String elevatorNum;

	    /**
	    *   上下扶梯数量
	     */
	    private String updownEscalatorNum;

	    /**
	    *   平行扶梯数量
	     */
	    private String parallelEscalatorNum;

	    /**
	    *   廊桥数量
	     */
	    private String loungeBridgeNum;

	    /**
	    *   出入口数量
	     */
	    private String entranceNum;

	    /**
	    *   考核标准
	     */
	    private String checkStandard;

	    /**
	    *   合同约束
	     */
	    private String contractRestrain;

	    /**
	    *   合同附件
	     */
	    private String contractFile;

	    /**
	    *   建筑平面图
	     */
	    private String architecturalPic;

	    /**
	    *   备注
	     */
	    private String remark;

	    /**
	    *   合同开始日期
	     */
	    private String startTimeStr;

	    /**
	    *   合同结束期
	     */
	    private String endTimeStr;

	    /**
	    *   进场日期
	     */
	    private String effectDateStr;

	    /**
	    *   区域编码
	     */
	    private String areaCode;
	    
	    private String areaName;
	    
		public String getAreaName() {
			return areaName;
		}

		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}

		public String getProgramId() {
			return programId;
		}

		public String getProgramCode() {
			return programCode;
		}

		public String getProgramName() {
			return programName;
		}

		public BigDecimal getCoveredArea() {
			return coveredArea;
		}

		public String getProgramStatus() {
			return programStatus;
		}

		public String getProgramAddress() {
			return programAddress;
		}

		public String getAcceptMode() {
			return acceptMode;
		}

		public BigDecimal getManagementArea() {
			return managementArea;
		}

		public String getBuildingStructure() {
			return buildingStructure;
		}

		public BigDecimal getContractMoney() {
			return contractMoney;
		}

		public String getContractDuration() {
			return contractDuration;
		}

		public String getPromisedNum() {
			return promisedNum;
		}

		public String getMinNum() {
			return minNum;
		}

		public String getRealNum() {
			return realNum;
		}

		public String getOfficeRoomNum() {
			return officeRoomNum;
		}

		public String getMeetingRoomNum() {
			return meetingRoomNum;
		}

		public String getWashroomNum() {
			return washroomNum;
		}

		public String getBabyCareRoomNum() {
			return babyCareRoomNum;
		}

		public String getSmokingRoomNum() {
			return smokingRoomNum;
		}

		public String getDrinkingFountainNum() {
			return drinkingFountainNum;
		}

		public String getElevatorNum() {
			return elevatorNum;
		}

		public String getUpdownEscalatorNum() {
			return updownEscalatorNum;
		}

		public String getParallelEscalatorNum() {
			return parallelEscalatorNum;
		}

		public String getLoungeBridgeNum() {
			return loungeBridgeNum;
		}

		public String getEntranceNum() {
			return entranceNum;
		}

		public String getCheckStandard() {
			return checkStandard;
		}

		public String getContractRestrain() {
			return contractRestrain;
		}

		public String getContractFile() {
			return contractFile;
		}

		public String getArchitecturalPic() {
			return architecturalPic;
		}

		public String getRemark() {
			return remark;
		}

		public String getStartTimeStr() {
			return startTimeStr;
		}

		public String getEndTimeStr() {
			return endTimeStr;
		}

		public String getEffectDateStr() {
			return effectDateStr;
		}

		public String getAreaCode() {
			return areaCode;
		}

		public void setProgramId(String programId) {
			this.programId = programId;
		}

		public void setProgramCode(String programCode) {
			this.programCode = programCode;
		}

		public void setProgramName(String programName) {
			this.programName = programName;
		}

		public void setCoveredArea(BigDecimal coveredArea) {
			this.coveredArea = coveredArea;
		}

		public void setProgramStatus(String programStatus) {
			this.programStatus = programStatus;
		}

		public void setProgramAddress(String programAddress) {
			this.programAddress = programAddress;
		}

		public void setAcceptMode(String acceptMode) {
			this.acceptMode = acceptMode;
		}

		public void setManagementArea(BigDecimal managementArea) {
			this.managementArea = managementArea;
		}

		public void setBuildingStructure(String buildingStructure) {
			this.buildingStructure = buildingStructure;
		}

		public void setContractMoney(BigDecimal contractMoney) {
			this.contractMoney = contractMoney;
		}

		public void setContractDuration(String contractDuration) {
			this.contractDuration = contractDuration;
		}

		public void setPromisedNum(String promisedNum) {
			this.promisedNum = promisedNum;
		}

		public void setMinNum(String minNum) {
			this.minNum = minNum;
		}

		public void setRealNum(String realNum) {
			this.realNum = realNum;
		}

		public void setOfficeRoomNum(String officeRoomNum) {
			this.officeRoomNum = officeRoomNum;
		}

		public void setMeetingRoomNum(String meetingRoomNum) {
			this.meetingRoomNum = meetingRoomNum;
		}

		public void setWashroomNum(String washroomNum) {
			this.washroomNum = washroomNum;
		}

		public void setBabyCareRoomNum(String babyCareRoomNum) {
			this.babyCareRoomNum = babyCareRoomNum;
		}

		public void setSmokingRoomNum(String smokingRoomNum) {
			this.smokingRoomNum = smokingRoomNum;
		}

		public void setDrinkingFountainNum(String drinkingFountainNum) {
			this.drinkingFountainNum = drinkingFountainNum;
		}

		public void setElevatorNum(String elevatorNum) {
			this.elevatorNum = elevatorNum;
		}

		public void setUpdownEscalatorNum(String updownEscalatorNum) {
			this.updownEscalatorNum = updownEscalatorNum;
		}

		public void setParallelEscalatorNum(String parallelEscalatorNum) {
			this.parallelEscalatorNum = parallelEscalatorNum;
		}

		public void setLoungeBridgeNum(String loungeBridgeNum) {
			this.loungeBridgeNum = loungeBridgeNum;
		}

		public void setEntranceNum(String entranceNum) {
			this.entranceNum = entranceNum;
		}

		public void setCheckStandard(String checkStandard) {
			this.checkStandard = checkStandard;
		}

		public void setContractRestrain(String contractRestrain) {
			this.contractRestrain = contractRestrain;
		}

		public void setContractFile(String contractFile) {
			this.contractFile = contractFile;
		}

		public void setArchitecturalPic(String architecturalPic) {
			this.architecturalPic = architecturalPic;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public void setStartTimeStr(String startTimeStr) {
			this.startTimeStr = startTimeStr;
		}

		public void setEndTimeStr(String endTimeStr) {
			this.endTimeStr = endTimeStr;
		}

		public void setEffectDateStr(String effectDateStr) {
			this.effectDateStr = effectDateStr;
		}

		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}

		@Override
		public String toString() {
			return "ProgramAddDto [programId=" + programId + ", programCode=" + programCode + ", programName="
					+ programName + ", coveredArea=" + coveredArea + ", programStatus=" + programStatus
					+ ", programAddress=" + programAddress + ", acceptMode=" + acceptMode + ", managementArea="
					+ managementArea + ", buildingStructure=" + buildingStructure + ", contractMoney=" + contractMoney
					+ ", contractDuration=" + contractDuration + ", promisedNum=" + promisedNum + ", minNum=" + minNum
					+ ", realNum=" + realNum + ", officeRoomNum=" + officeRoomNum + ", meetingRoomNum=" + meetingRoomNum
					+ ", washroomNum=" + washroomNum + ", babyCareRoomNum=" + babyCareRoomNum + ", smokingRoomNum="
					+ smokingRoomNum + ", drinkingFountainNum=" + drinkingFountainNum + ", elevatorNum=" + elevatorNum
					+ ", updownEscalatorNum=" + updownEscalatorNum + ", parallelEscalatorNum=" + parallelEscalatorNum
					+ ", loungeBridgeNum=" + loungeBridgeNum + ", entranceNum=" + entranceNum + ", checkStandard="
					+ checkStandard + ", contractRestrain=" + contractRestrain + ", contractFile=" + contractFile
					+ ", architecturalPic=" + architecturalPic + ", remark=" + remark + ", startTimeStr=" + startTimeStr
					+ ", endTimeStr=" + endTimeStr + ", effectDateStr=" + effectDateStr + ", areaCode=" + areaCode
					+ ", areaName=" + areaName + "]";
		}


	    
}
