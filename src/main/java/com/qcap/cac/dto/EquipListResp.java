package com.qcap.cac.dto;

public class EquipListResp{

//    private String equipId;
//    private String equipNo;
//    private String equipName;
    private String url;
    private String useTime;
//    private String storeAreaName;
//    private String status;
//    private String statusName;
//    private String lineNo;
    private String equipTypeCode;
    private String equipTypeName;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getEquipTypeCode() {
        return equipTypeCode;
    }

    public void setEquipTypeCode(String equipTypeCode) {
        this.equipTypeCode = equipTypeCode;
    }

    public String getEquipTypeName() {
        return equipTypeName;
    }

    public void setEquipTypeName(String equipTypeName) {
        this.equipTypeName = equipTypeName;
    }

    @Override
    public String toString() {
        return "EquipListResp{" +
                "url='" + url + '\'' +
                ", useTime='" + useTime + '\'' +
                ", equipTypeCode='" + equipTypeCode + '\'' +
                ", equipTypeName='" + equipTypeName + '\'' +
                '}';
    }
}
