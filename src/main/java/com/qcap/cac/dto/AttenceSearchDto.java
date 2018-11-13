package com.qcap.cac.dto;

public class AttenceSearchDto {

    private String workNo;
    private String personName;
    private String attenceTime;
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

    public String getAttenceTime() {
        return attenceTime;
    }

    public void setAttenceTime(String attenceTime) {
        this.attenceTime = attenceTime;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    @Override
    public String toString() {
        return "AttenceSearchDto{" +
                "workNo='" + workNo + '\'' +
                ", personName='" + personName + '\'' +
                ", attenceTime='" + attenceTime + '\'' +
                ", programCode='" + programCode + '\'' +
                '}';
    }
}
