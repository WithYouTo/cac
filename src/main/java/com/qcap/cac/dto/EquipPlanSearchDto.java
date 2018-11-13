package com.qcap.cac.dto;

public class EquipPlanSearchDto {

    private String equipType;
    private String equipNo;
    private String equipModel;
    private String nextMaintTime;
    private String programCode;

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getEquipModel() {
        return equipModel;
    }

    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel;
    }

    public String getNextMaintTime() {
        return nextMaintTime;
    }

    public void setNextMaintTime(String nextMaintTime) {
        this.nextMaintTime = nextMaintTime;
    }

    public String getProgramCode() {
        return programCode;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    @Override
    public String toString() {
        return "EquipPlanSearchDto{" +
                "equipType='" + equipType + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", equipModel='" + equipModel + '\'' +
                ", nextMaintTime='" + nextMaintTime + '\'' +
                ", programCode='" + programCode + '\'' +
                '}';
    }
}
