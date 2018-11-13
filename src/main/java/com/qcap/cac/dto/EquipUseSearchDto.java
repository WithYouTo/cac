package com.qcap.cac.dto;

public class EquipUseSearchDto {
    private String area;
    private String equipNo;
    private String startUseTime;
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

    public String getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(String startUseTime) {
        this.startUseTime = startUseTime;
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
        return "EquipUseSearchDto{" +
                "area='" + area + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", startUseTime='" + startUseTime + '\'' +
                ", status='" + status + '\'' +
                ", programCode='" + programCode + '\'' +
                '}';
    }
}
