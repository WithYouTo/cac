package com.qcap.cac.dto;

public class EquipRepairSearchDto {

    private String equipType;
    private String equipName;
    private String repairTime;
    private String status;

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(String repairTime) {
        this.repairTime = repairTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EquipRepairSearchDto{" +
                "equipType='" + equipType + '\'' +
                ", equipName='" + equipName + '\'' +
                ", repairTime='" + repairTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
