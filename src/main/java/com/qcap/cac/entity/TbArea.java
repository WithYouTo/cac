package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class TbArea {
    /**
    *   主键
     */
    @TableId("AREA_ID")
    private String areaId;

    /**
    *   区域编码
     */
    private String areaCode;

    /**
    *   区域名称
     */
    private String areaName;

    /**
    *   房间类型(区域类型)
     */
    private String areaType;

    /**
    *   上级区域编码
     */
    private String superAreaCode;

    /**
    *   层级
     */
    private String level;

    /**
    *   序号
     */
    private String seqNo;

    /**
    *   最终节点标识
     */
    private String finalFlag;

    /**
    *   建筑物用途
     */
    private String buildingPurpose;

    /**
    *   建筑房间号码
     */
    private String buildingCode;

    /**
    *   房间号码
     */
    private String roomCode;

    /**
    *   门资产编码
     */
    private String doorCode;

    /**
    *   房间代码
     */
    private String roomNo;

    /**
    *   房间区域
     */
    private String roomArea;

    /**
    *   面积
     */
    private String acreage;

    /**
    *   周长
     */
    private String perimeter;

    /**
    *   备注1
     */
    private String remark1;

    /**
    *   备注2
     */
    private String remark2;

    /**
    *   备注3
     */
    private String remark3;

    /**
    *   新增人
     */
    private String createEmp;

    /**
    *   新增时间
     */
    private String createDate;

    /**
    *   修改时间
     */
    private String updateDate;

    /**
    *   修改人
     */
    private String updateEmp;

    /**
    *   版本
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.AREA_ID
     *
     * @return the value of tb_area.AREA_ID
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.AREA_ID
     *
     * @param areaId the value for tb_area.AREA_ID
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.AREA_CODE
     *
     * @return the value of tb_area.AREA_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.AREA_CODE
     *
     * @param areaCode the value for tb_area.AREA_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.AREA_NAME
     *
     * @return the value of tb_area.AREA_NAME
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.AREA_NAME
     *
     * @param areaName the value for tb_area.AREA_NAME
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.AREA_TYPE
     *
     * @return the value of tb_area.AREA_TYPE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getAreaType() {
        return areaType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.AREA_TYPE
     *
     * @param areaType the value for tb_area.AREA_TYPE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.SUPER_AREA_CODE
     *
     * @return the value of tb_area.SUPER_AREA_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getSuperAreaCode() {
        return superAreaCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.SUPER_AREA_CODE
     *
     * @param superAreaCode the value for tb_area.SUPER_AREA_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setSuperAreaCode(String superAreaCode) {
        this.superAreaCode = superAreaCode == null ? null : superAreaCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.LEVEL
     *
     * @return the value of tb_area.LEVEL
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.LEVEL
     *
     * @param level the value for tb_area.LEVEL
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.SEQ_NO
     *
     * @return the value of tb_area.SEQ_NO
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.SEQ_NO
     *
     * @param seqNo the value for tb_area.SEQ_NO
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.FINAL_FLAG
     *
     * @return the value of tb_area.FINAL_FLAG
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getFinalFlag() {
        return finalFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.FINAL_FLAG
     *
     * @param finalFlag the value for tb_area.FINAL_FLAG
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setFinalFlag(String finalFlag) {
        this.finalFlag = finalFlag == null ? null : finalFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.BUILDING_PURPOSE
     *
     * @return the value of tb_area.BUILDING_PURPOSE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getBuildingPurpose() {
        return buildingPurpose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.BUILDING_PURPOSE
     *
     * @param buildingPurpose the value for tb_area.BUILDING_PURPOSE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setBuildingPurpose(String buildingPurpose) {
        this.buildingPurpose = buildingPurpose == null ? null : buildingPurpose.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.BUILDING_CODE
     *
     * @return the value of tb_area.BUILDING_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getBuildingCode() {
        return buildingCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.BUILDING_CODE
     *
     * @param buildingCode the value for tb_area.BUILDING_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode == null ? null : buildingCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.ROOM_CODE
     *
     * @return the value of tb_area.ROOM_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.ROOM_CODE
     *
     * @param roomCode the value for tb_area.ROOM_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.DOOR_CODE
     *
     * @return the value of tb_area.DOOR_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getDoorCode() {
        return doorCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.DOOR_CODE
     *
     * @param doorCode the value for tb_area.DOOR_CODE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setDoorCode(String doorCode) {
        this.doorCode = doorCode == null ? null : doorCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.ROOM_NO
     *
     * @return the value of tb_area.ROOM_NO
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRoomNo() {
        return roomNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.ROOM_NO
     *
     * @param roomNo the value for tb_area.ROOM_NO
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo == null ? null : roomNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.ROOM_AREA
     *
     * @return the value of tb_area.ROOM_AREA
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRoomArea() {
        return roomArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.ROOM_AREA
     *
     * @param roomArea the value for tb_area.ROOM_AREA
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea == null ? null : roomArea.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.ACREAGE
     *
     * @return the value of tb_area.ACREAGE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getAcreage() {
        return acreage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.ACREAGE
     *
     * @param acreage the value for tb_area.ACREAGE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setAcreage(String acreage) {
        this.acreage = acreage == null ? null : acreage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.PERIMETER
     *
     * @return the value of tb_area.PERIMETER
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getPerimeter() {
        return perimeter;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.PERIMETER
     *
     * @param perimeter the value for tb_area.PERIMETER
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setPerimeter(String perimeter) {
        this.perimeter = perimeter == null ? null : perimeter.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.REMARK1
     *
     * @return the value of tb_area.REMARK1
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRemark1() {
        return remark1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.REMARK1
     *
     * @param remark1 the value for tb_area.REMARK1
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.REMARK2
     *
     * @return the value of tb_area.REMARK2
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRemark2() {
        return remark2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.REMARK2
     *
     * @param remark2 the value for tb_area.REMARK2
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.REMARK3
     *
     * @return the value of tb_area.REMARK3
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getRemark3() {
        return remark3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.REMARK3
     *
     * @param remark3 the value for tb_area.REMARK3
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.CREATE_EMP
     *
     * @return the value of tb_area.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.CREATE_EMP
     *
     * @param createEmp the value for tb_area.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.CREATE_DATE
     *
     * @return the value of tb_area.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.CREATE_DATE
     *
     * @param createDate the value for tb_area.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.UPDATE_DATE
     *
     * @return the value of tb_area.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.UPDATE_DATE
     *
     * @param updateDate the value for tb_area.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.UPDATE_EMP
     *
     * @return the value of tb_area.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.UPDATE_EMP
     *
     * @param updateEmp the value for tb_area.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_area.VERSION
     *
     * @return the value of tb_area.VERSION
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_area.VERSION
     *
     * @param version the value for tb_area.VERSION
     *
     * @mbggenerated Tue Oct 09 15:30:51 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}