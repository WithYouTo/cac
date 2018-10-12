package com.qcap.cac.dto;

public class EquipMaintInsertParam {

    private String maintType;
    private String equipName;
    private String equipNo;
    private String partsNo;
    private String maintDesc;

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

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo;
    }

    public String getMaintDesc() {
        return maintDesc;
    }

    public void setMaintDesc(String maintDesc) {
        this.maintDesc = maintDesc;
    }
}
