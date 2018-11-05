package com.qcap.cac.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TbProgram {
    /**
    *   项目ID
     */
    private String programId;

    /**
    *   项目编号
     */
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
    private Date startTime;

    /**
    *   合同结束期
     */
    private Date endTime;

    /**
    *   进场日期
     */
    private Date effectDate;

    /**
    *   区域编码
     */
    private String areaCode;

    /**
    *   区域名称
     */
    private String areaName;

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
     * This method returns the value of the database column tb_program.PROGRAM_ID
     *
     * @return the value of tb_program.PROGRAM_ID
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getProgramId() {
        return programId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.PROGRAM_ID
     *
     * @param programId the value for tb_program.PROGRAM_ID
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setProgramId(String programId) {
        this.programId = programId == null ? null : programId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.PROGRAM_CODE
     *
     * @return the value of tb_program.PROGRAM_CODE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.PROGRAM_CODE
     *
     * @param programCode the value for tb_program.PROGRAM_CODE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode == null ? null : programCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.PROGRAM_NAME
     *
     * @return the value of tb_program.PROGRAM_NAME
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.PROGRAM_NAME
     *
     * @param programName the value for tb_program.PROGRAM_NAME
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setProgramName(String programName) {
        this.programName = programName == null ? null : programName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.COVERED_AREA
     *
     * @return the value of tb_program.COVERED_AREA
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public BigDecimal getCoveredArea() {
        return coveredArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.COVERED_AREA
     *
     * @param coveredArea the value for tb_program.COVERED_AREA
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setCoveredArea(BigDecimal coveredArea) {
        this.coveredArea = coveredArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.PROGRAM_STATUS
     *
     * @return the value of tb_program.PROGRAM_STATUS
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getProgramStatus() {
        return programStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.PROGRAM_STATUS
     *
     * @param programStatus the value for tb_program.PROGRAM_STATUS
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setProgramStatus(String programStatus) {
        this.programStatus = programStatus == null ? null : programStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.PROGRAM_ADDRESS
     *
     * @return the value of tb_program.PROGRAM_ADDRESS
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getProgramAddress() {
        return programAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.PROGRAM_ADDRESS
     *
     * @param programAddress the value for tb_program.PROGRAM_ADDRESS
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setProgramAddress(String programAddress) {
        this.programAddress = programAddress == null ? null : programAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.ACCEPT_MODE
     *
     * @return the value of tb_program.ACCEPT_MODE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getAcceptMode() {
        return acceptMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.ACCEPT_MODE
     *
     * @param acceptMode the value for tb_program.ACCEPT_MODE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setAcceptMode(String acceptMode) {
        this.acceptMode = acceptMode == null ? null : acceptMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.MANAGEMENT_AREA
     *
     * @return the value of tb_program.MANAGEMENT_AREA
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public BigDecimal getManagementArea() {
        return managementArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.MANAGEMENT_AREA
     *
     * @param managementArea the value for tb_program.MANAGEMENT_AREA
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setManagementArea(BigDecimal managementArea) {
        this.managementArea = managementArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.BUILDING_STRUCTURE
     *
     * @return the value of tb_program.BUILDING_STRUCTURE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getBuildingStructure() {
        return buildingStructure;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.BUILDING_STRUCTURE
     *
     * @param buildingStructure the value for tb_program.BUILDING_STRUCTURE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure == null ? null : buildingStructure.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.CONTRACT_MONEY
     *
     * @return the value of tb_program.CONTRACT_MONEY
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public BigDecimal getContractMoney() {
        return contractMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.CONTRACT_MONEY
     *
     * @param contractMoney the value for tb_program.CONTRACT_MONEY
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setContractMoney(BigDecimal contractMoney) {
        this.contractMoney = contractMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.CONTRACT_DURATION
     *
     * @return the value of tb_program.CONTRACT_DURATION
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getContractDuration() {
        return contractDuration;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.CONTRACT_DURATION
     *
     * @param contractDuration the value for tb_program.CONTRACT_DURATION
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setContractDuration(String contractDuration) {
        this.contractDuration = contractDuration == null ? null : contractDuration.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.PROMISED_NUM
     *
     * @return the value of tb_program.PROMISED_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getPromisedNum() {
        return promisedNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.PROMISED_NUM
     *
     * @param promisedNum the value for tb_program.PROMISED_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setPromisedNum(String promisedNum) {
        this.promisedNum = promisedNum == null ? null : promisedNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.MIN_NUM
     *
     * @return the value of tb_program.MIN_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getMinNum() {
        return minNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.MIN_NUM
     *
     * @param minNum the value for tb_program.MIN_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setMinNum(String minNum) {
        this.minNum = minNum == null ? null : minNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.REAL_NUM
     *
     * @return the value of tb_program.REAL_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getRealNum() {
        return realNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.REAL_NUM
     *
     * @param realNum the value for tb_program.REAL_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setRealNum(String realNum) {
        this.realNum = realNum == null ? null : realNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.OFFICE_ROOM_NUM
     *
     * @return the value of tb_program.OFFICE_ROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getOfficeRoomNum() {
        return officeRoomNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.OFFICE_ROOM_NUM
     *
     * @param officeRoomNum the value for tb_program.OFFICE_ROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setOfficeRoomNum(String officeRoomNum) {
        this.officeRoomNum = officeRoomNum == null ? null : officeRoomNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.MEETING_ROOM_NUM
     *
     * @return the value of tb_program.MEETING_ROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getMeetingRoomNum() {
        return meetingRoomNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.MEETING_ROOM_NUM
     *
     * @param meetingRoomNum the value for tb_program.MEETING_ROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setMeetingRoomNum(String meetingRoomNum) {
        this.meetingRoomNum = meetingRoomNum == null ? null : meetingRoomNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.WASHROOM_NUM
     *
     * @return the value of tb_program.WASHROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getWashroomNum() {
        return washroomNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.WASHROOM_NUM
     *
     * @param washroomNum the value for tb_program.WASHROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setWashroomNum(String washroomNum) {
        this.washroomNum = washroomNum == null ? null : washroomNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.BABY_CARE_ROOM_NUM
     *
     * @return the value of tb_program.BABY_CARE_ROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getBabyCareRoomNum() {
        return babyCareRoomNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.BABY_CARE_ROOM_NUM
     *
     * @param babyCareRoomNum the value for tb_program.BABY_CARE_ROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setBabyCareRoomNum(String babyCareRoomNum) {
        this.babyCareRoomNum = babyCareRoomNum == null ? null : babyCareRoomNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.SMOKING_ROOM_NUM
     *
     * @return the value of tb_program.SMOKING_ROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getSmokingRoomNum() {
        return smokingRoomNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.SMOKING_ROOM_NUM
     *
     * @param smokingRoomNum the value for tb_program.SMOKING_ROOM_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setSmokingRoomNum(String smokingRoomNum) {
        this.smokingRoomNum = smokingRoomNum == null ? null : smokingRoomNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.DRINKING_FOUNTAIN_NUM
     *
     * @return the value of tb_program.DRINKING_FOUNTAIN_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getDrinkingFountainNum() {
        return drinkingFountainNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.DRINKING_FOUNTAIN_NUM
     *
     * @param drinkingFountainNum the value for tb_program.DRINKING_FOUNTAIN_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setDrinkingFountainNum(String drinkingFountainNum) {
        this.drinkingFountainNum = drinkingFountainNum == null ? null : drinkingFountainNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.ELEVATOR_NUM
     *
     * @return the value of tb_program.ELEVATOR_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getElevatorNum() {
        return elevatorNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.ELEVATOR_NUM
     *
     * @param elevatorNum the value for tb_program.ELEVATOR_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setElevatorNum(String elevatorNum) {
        this.elevatorNum = elevatorNum == null ? null : elevatorNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.UPDOWN_ESCALATOR_NUM
     *
     * @return the value of tb_program.UPDOWN_ESCALATOR_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getUpdownEscalatorNum() {
        return updownEscalatorNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.UPDOWN_ESCALATOR_NUM
     *
     * @param updownEscalatorNum the value for tb_program.UPDOWN_ESCALATOR_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setUpdownEscalatorNum(String updownEscalatorNum) {
        this.updownEscalatorNum = updownEscalatorNum == null ? null : updownEscalatorNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.PARALLEL_ESCALATOR_NUM
     *
     * @return the value of tb_program.PARALLEL_ESCALATOR_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getParallelEscalatorNum() {
        return parallelEscalatorNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.PARALLEL_ESCALATOR_NUM
     *
     * @param parallelEscalatorNum the value for tb_program.PARALLEL_ESCALATOR_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setParallelEscalatorNum(String parallelEscalatorNum) {
        this.parallelEscalatorNum = parallelEscalatorNum == null ? null : parallelEscalatorNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.LOUNGE_BRIDGE_NUM
     *
     * @return the value of tb_program.LOUNGE_BRIDGE_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getLoungeBridgeNum() {
        return loungeBridgeNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.LOUNGE_BRIDGE_NUM
     *
     * @param loungeBridgeNum the value for tb_program.LOUNGE_BRIDGE_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setLoungeBridgeNum(String loungeBridgeNum) {
        this.loungeBridgeNum = loungeBridgeNum == null ? null : loungeBridgeNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.ENTRANCE_NUM
     *
     * @return the value of tb_program.ENTRANCE_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getEntranceNum() {
        return entranceNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.ENTRANCE_NUM
     *
     * @param entranceNum the value for tb_program.ENTRANCE_NUM
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setEntranceNum(String entranceNum) {
        this.entranceNum = entranceNum == null ? null : entranceNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.CHECK_STANDARD
     *
     * @return the value of tb_program.CHECK_STANDARD
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getCheckStandard() {
        return checkStandard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.CHECK_STANDARD
     *
     * @param checkStandard the value for tb_program.CHECK_STANDARD
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setCheckStandard(String checkStandard) {
        this.checkStandard = checkStandard == null ? null : checkStandard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.CONTRACT_RESTRAIN
     *
     * @return the value of tb_program.CONTRACT_RESTRAIN
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getContractRestrain() {
        return contractRestrain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.CONTRACT_RESTRAIN
     *
     * @param contractRestrain the value for tb_program.CONTRACT_RESTRAIN
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setContractRestrain(String contractRestrain) {
        this.contractRestrain = contractRestrain == null ? null : contractRestrain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.CONTRACT_FILE
     *
     * @return the value of tb_program.CONTRACT_FILE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getContractFile() {
        return contractFile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.CONTRACT_FILE
     *
     * @param contractFile the value for tb_program.CONTRACT_FILE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setContractFile(String contractFile) {
        this.contractFile = contractFile == null ? null : contractFile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.ARCHITECTURAL_PIC
     *
     * @return the value of tb_program.ARCHITECTURAL_PIC
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getArchitecturalPic() {
        return architecturalPic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.ARCHITECTURAL_PIC
     *
     * @param architecturalPic the value for tb_program.ARCHITECTURAL_PIC
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setArchitecturalPic(String architecturalPic) {
        this.architecturalPic = architecturalPic == null ? null : architecturalPic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.REMARK
     *
     * @return the value of tb_program.REMARK
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.REMARK
     *
     * @param remark the value for tb_program.REMARK
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.START_TIME
     *
     * @return the value of tb_program.START_TIME
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.START_TIME
     *
     * @param startTime the value for tb_program.START_TIME
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.END_TIME
     *
     * @return the value of tb_program.END_TIME
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.END_TIME
     *
     * @param endTime the value for tb_program.END_TIME
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.EFFECT_DATE
     *
     * @return the value of tb_program.EFFECT_DATE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public Date getEffectDate() {
        return effectDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.EFFECT_DATE
     *
     * @param effectDate the value for tb_program.EFFECT_DATE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.AREA_CODE
     *
     * @return the value of tb_program.AREA_CODE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.AREA_CODE
     *
     * @param areaCode the value for tb_program.AREA_CODE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.AREA_NAME
     *
     * @return the value of tb_program.AREA_NAME
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.AREA_NAME
     *
     * @param areaName the value for tb_program.AREA_NAME
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.REMARK1
     *
     * @return the value of tb_program.REMARK1
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.REMARK1
     *
     * @param remark1 the value for tb_program.REMARK1
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.REMARK2
     *
     * @return the value of tb_program.REMARK2
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.REMARK2
     *
     * @param remark2 the value for tb_program.REMARK2
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.REMARK3
     *
     * @return the value of tb_program.REMARK3
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.REMARK3
     *
     * @param remark3 the value for tb_program.REMARK3
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.CREATE_EMP
     *
     * @return the value of tb_program.CREATE_EMP
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.CREATE_EMP
     *
     * @param createEmp the value for tb_program.CREATE_EMP
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.CREATE_DATE
     *
     * @return the value of tb_program.CREATE_DATE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.CREATE_DATE
     *
     * @param createDate the value for tb_program.CREATE_DATE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.UPDATE_DATE
     *
     * @return the value of tb_program.UPDATE_DATE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.UPDATE_DATE
     *
     * @param updateDate the value for tb_program.UPDATE_DATE
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.UPDATE_EMP
     *
     * @return the value of tb_program.UPDATE_EMP
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.UPDATE_EMP
     *
     * @param updateEmp the value for tb_program.UPDATE_EMP
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_program.VERSION
     *
     * @return the value of tb_program.VERSION
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_program.VERSION
     *
     * @param version the value for tb_program.VERSION
     *
     * @mbggenerated Fri Nov 02 15:10:47 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}