package com.qcap.cac.dto;

public class EquipPlanSearchParam {

    private String equipType;
    private String equipName;
    private String equipModel;
    private String latestMaintTime;

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
}
