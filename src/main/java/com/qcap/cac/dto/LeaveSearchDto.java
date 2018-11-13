package com.qcap.cac.dto;

public class LeaveSearchDto {

    private String workNo;
    private String personName;
    private String leaveStartTime;
    private String programCode;

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getLeaveStartTime() {
        return leaveStartTime;
    }

    public void setLeaveStartTime(String leaveStartTime) {
        this.leaveStartTime = leaveStartTime;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    @Override
    public String toString() {
        return "LeaveSearchDto{" +
                "workNo='" + workNo + '\'' +
                ", personName='" + personName + '\'' +
                ", leaveStartTime='" + leaveStartTime + '\'' +
                ", programCode='" + programCode + '\'' +
                '}';
    }
}
