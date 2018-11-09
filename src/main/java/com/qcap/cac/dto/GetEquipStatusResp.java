package com.qcap.cac.dto;

import java.util.List;
import java.util.Map;

public class GetEquipStatusResp {

    private String url;
    private String equipName;
    private String equipNo;
    private String equipType;
    private String status;
    private String statusName;
    private String stopPlace;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStopPlace() {
        return stopPlace;
    }

    public void setStopPlace(String stopPlace) {
        this.stopPlace = stopPlace;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    @Override
    public String toString() {
        return "GetEquipStatusResp{" +
                "url='" + url + '\'' +
                ", equipName='" + equipName + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", equipType='" + equipType + '\'' +
                ", status='" + status + '\'' +
                ", statusName='" + statusName + '\'' +
                ", stopPlace='" + stopPlace + '\'' +
                '}';
    }
}
