package com.qcap.cac.dto;

public class EquipMaintSearchDto {

    private String maintType;
    private String equipName;
    private String partsName;

    public String getMaintType() {
        return maintType;
    }

    public void setMaintType(String maintType) {
        this.maintType = maintType;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    @Override
    public String toString() {
        return "EquipMaintSearchDto{" +
                "maintType='" + maintType + '\'' +
                ", equipName='" + equipName + '\'' +
                ", partsName='" + partsName + '\'' +
                '}';
    }
}
