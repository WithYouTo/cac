package com.qcap.cac.dto;

public class EquipChargeSearchDto {

    private String area;
    private String equipNo;
    private String startChargeTime;
    private String status;
    private String programCode;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getStartChargeTime() {
        return startChargeTime;
    }

    public void setStartChargeTime(String startChargeTime) {
        this.startChargeTime = startChargeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    @Override
    public String toString() {
        return "EquipChargeSearchDto{" +
                "area='" + area + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", startChargeTime='" + startChargeTime + '\'' +
                ", status='" + status + '\'' +
                ", programCode='" + programCode + '\'' +
                '}';
    }
}
