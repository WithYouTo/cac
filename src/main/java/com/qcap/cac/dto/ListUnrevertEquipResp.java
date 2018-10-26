package com.qcap.cac.dto;

public class ListUnrevertEquipResp {

    private String equipNo;
    private String equipName;
    private String url;
    private String useTime;
    private String storeAreaName;
    private String status;
    private String statusName;
    private String lineNo;

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

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

    public String getStoreAreaName() {
        return storeAreaName;
    }

    public void setStoreAreaName(String storeAreaName) {
        this.storeAreaName = storeAreaName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    @Override
    public String toString() {
        return "ListUnrevertEquipResp{" +
                "equipNo='" + equipNo + '\'' +
                ", equipName='" + equipName + '\'' +
                ", url='" + url + '\'' +
                ", useTime='" + useTime + '\'' +
                ", storeAreaName='" + storeAreaName + '\'' +
                ", status='" + status + '\'' +
                ", statusName='" + statusName + '\'' +
                ", lineNo='" + lineNo + '\'' +
                '}';
    }
}
