package com.qcap.cac.dto;

public class EquipSearchDto {

    private String equipName;
    private String equipType;
    private String equipState;
    private String equipWorkState;
    private String programCode;

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipState() {
        return equipState;
    }

    public void setEquipState(String equipState) {
        this.equipState = equipState;
    }

    public String getEquipWorkState() {
        return equipWorkState;
    }

    public void setEquipWorkState(String equipWorkState) {
        this.equipWorkState = equipWorkState;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    @Override
    public String toString() {
        return "EquipSearchDto{" +
                "equipName='" + equipName + '\'' +
                ", equipType='" + equipType + '\'' +
                ", equipState='" + equipState + '\'' +
                ", equipWorkState='" + equipWorkState + '\'' +
                ", programCode='" + programCode + '\'' +
                '}';
    }
}
