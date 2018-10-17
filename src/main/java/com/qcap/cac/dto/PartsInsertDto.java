package com.qcap.cac.dto;

public class PartsInsertDto {

    private String equipId;
    private String partsId;
    private String partsName;
    private String partsModel;
    private String partsAgence;
    private String maintCycle;
    private String relatePerson;
    private String relateMobile;

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getPartsId() {
        return partsId;
    }

    public void setPartsId(String partsId) {
        this.partsId = partsId;
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getPartsModel() {
        return partsModel;
    }

    public void setPartsModel(String partsModel) {
        this.partsModel = partsModel;
    }

    public String getPartsAgence() {
        return partsAgence;
    }

    public void setPartsAgence(String partsAgence) {
        this.partsAgence = partsAgence;
    }

    public String getMaintCycle() {
        return maintCycle;
    }

    public void setMaintCycle(String maintCycle) {
        this.maintCycle = maintCycle;
    }

    public String getRelatePerson() {
        return relatePerson;
    }

    public void setRelatePerson(String relatePerson) {
        this.relatePerson = relatePerson;
    }

    public String getRelateMobile() {
        return relateMobile;
    }

    public void setRelateMobile(String relateMobile) {
        this.relateMobile = relateMobile;
    }

    @Override
    public String toString() {
        return "PartsInsertDto{" +
                "partsName='" + partsName + '\'' +
                ", partsModel='" + partsModel + '\'' +
                ", partsAgence='" + partsAgence + '\'' +
                ", maintCycle='" + maintCycle + '\'' +
                ", relatePerson='" + relatePerson + '\'' +
                ", relateMobile='" + relateMobile + '\'' +
                '}';
    }
}
