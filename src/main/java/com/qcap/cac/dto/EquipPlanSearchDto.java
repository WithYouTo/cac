package com.qcap.cac.dto;

public class EquipPlanSearchDto {

    private String equipType;
    private String equipNo;
    private String equipModel;
    private String latestMaintTime;

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

    public String getLatestMaintTime() {
        return latestMaintTime;
    }

    public void setLatestMaintTime(String latestMaintTime) {
        this.latestMaintTime = latestMaintTime;
    }

    @Override
    public String toString() {
        return "EquipPlanSearchDto{" +
                "equipType='" + equipType + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", equipModel='" + equipModel + '\'' +
                ", latestMaintTime='" + latestMaintTime + '\'' +
                '}';
    }
}
