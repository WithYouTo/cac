package com.qcap.cac.dto;

public class EquipMaintInsertDto {

    private String maintType;
    private String equipName;
    private String equipNo;
    private String partsNo;
    private String personName;
    private String maintTime;
    private String maintMoney;
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

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getMaintTime() {
        return maintTime;
    }

    public void setMaintTime(String maintTime) {
        this.maintTime = maintTime;
    }

    public String getMaintDesc() {
        return maintDesc;
    }

    public void setMaintDesc(String maintDesc) {
        this.maintDesc = maintDesc;
    }

    public String getMaintMoney() {
        return maintMoney;
    }

    public void setMaintMoney(String maintMoney) {
        this.maintMoney = maintMoney;
    }

    @Override
    public String toString() {
        return "EquipMaintInsertDto{" +
                "maintType='" + maintType + '\'' +
                ", equipName='" + equipName + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", partsNo='" + partsNo + '\'' +
                ", personName='" + personName + '\'' +
                ", maintTime='" + maintTime + '\'' +
                ", maintMoney='" + maintMoney + '\'' +
                ", maintDesc='" + maintDesc + '\'' +
                '}';
    }
}
