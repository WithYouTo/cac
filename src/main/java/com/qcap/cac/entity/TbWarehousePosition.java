package com.qcap.cac.entity;

import java.util.Date;

public class TbWarehousePosition {
    /**
     *   库位主键ID
     */
    private String warehousePositionId;

    /**
     *   储藏室
     */
    private String storeroomId;

    /**
     *   名称
     */
    private String storeroom;

    /**
     *   建筑物编码
     */
    private String buildingCode;

    /**
     *   建筑物
     */
    private String buildingName;

    /**
     *   楼层编码
     */
    private String floorCode;

    /**
     *   楼层
     */
    private String floorName;

    /**
     *   房间编码
     */
    private String roomCode;

    /**
     *   室
     */
    private String roomName;

    /**
     *   区柜架
     */
    private String rangeShelf;

    /**
     *   说明
     */
    private String instruction;

    /**
     *   删除标志(Y删除N未删除)
     */
    private String deleteFlag;

    /**
     *   新增人
     */
    private String createEmp;

    /**
     *   新增时间
     */
    private Date createDate;

    /**
     *   修改人
     */
    private String updateEmp;

    /**
     *   修改时间
     */
    private Date updateDate;

    /**
     *   版本
     */
    private Integer version;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.WAREHOUSE_POSITION_ID
     *
     * @return the value of tb_warehouse_position.WAREHOUSE_POSITION_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getWarehousePositionId() {
        return warehousePositionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.WAREHOUSE_POSITION_ID
     *
     * @param warehousePositionId the value for tb_warehouse_position.WAREHOUSE_POSITION_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setWarehousePositionId(String warehousePositionId) {
        this.warehousePositionId = warehousePositionId == null ? null : warehousePositionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.STOREROOM_ID
     *
     * @return the value of tb_warehouse_position.STOREROOM_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getStoreroomId() {
        return storeroomId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.STOREROOM_ID
     *
     * @param storeroomId the value for tb_warehouse_position.STOREROOM_ID
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setStoreroomId(String storeroomId) {
        this.storeroomId = storeroomId == null ? null : storeroomId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.STOREROOM
     *
     * @return the value of tb_warehouse_position.STOREROOM
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getStoreroom() {
        return storeroom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.STOREROOM
     *
     * @param storeroom the value for tb_warehouse_position.STOREROOM
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setStoreroom(String storeroom) {
        this.storeroom = storeroom == null ? null : storeroom.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.BUILDING_CODE
     *
     * @return the value of tb_warehouse_position.BUILDING_CODE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getBuildingCode() {
        return buildingCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.BUILDING_CODE
     *
     * @param buildingCode the value for tb_warehouse_position.BUILDING_CODE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode == null ? null : buildingCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.BULIDING_NAME
     *
     * @return the value of tb_warehouse_position.BULIDING_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getBuildingName() {
        return buildingName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.BUILDING_NAME
     *
     * @param buildingName the value for tb_warehouse_position.BUILDING_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName == null ? null : buildingName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.FLOOR_CODE
     *
     * @return the value of tb_warehouse_position.FLOOR_CODE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getFloorCode() {
        return floorCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.FLOOR_CODE
     *
     * @param floorCode the value for tb_warehouse_position.FLOOR_CODE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode == null ? null : floorCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.FLOOR_NAME
     *
     * @return the value of tb_warehouse_position.FLOOR_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getFloorName() {
        return floorName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.FLOOR_NAME
     *
     * @param floorName the value for tb_warehouse_position.FLOOR_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setFloorName(String floorName) {
        this.floorName = floorName == null ? null : floorName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.ROOM_CODE
     *
     * @return the value of tb_warehouse_position.ROOM_CODE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRoomCode() {
        return roomCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.ROOM_CODE
     *
     * @param roomCode the value for tb_warehouse_position.ROOM_CODE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.ROOM_NAME
     *
     * @return the value of tb_warehouse_position.ROOM_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.ROOM_NAME
     *
     * @param roomName the value for tb_warehouse_position.ROOM_NAME
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.RANGE_SHELF
     *
     * @return the value of tb_warehouse_position.RANGE_SHELF
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getRangeShelf() {
        return rangeShelf;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.RANGE_SHELF
     *
     * @param rangeShelf the value for tb_warehouse_position.RANGE_SHELF
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setRangeShelf(String rangeShelf) {
        this.rangeShelf = rangeShelf == null ? null : rangeShelf.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.INSTRUCTION
     *
     * @return the value of tb_warehouse_position.INSTRUCTION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.INSTRUCTION
     *
     * @param instruction the value for tb_warehouse_position.INSTRUCTION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction == null ? null : instruction.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.DELETE_FLAG
     *
     * @return the value of tb_warehouse_position.DELETE_FLAG
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.DELETE_FLAG
     *
     * @param deleteFlag the value for tb_warehouse_position.DELETE_FLAG
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.CREATE_EMP
     *
     * @return the value of tb_warehouse_position.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getCreateEmp() {
        return createEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.CREATE_EMP
     *
     * @param createEmp the value for tb_warehouse_position.CREATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp == null ? null : createEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.CREATE_DATE
     *
     * @return the value of tb_warehouse_position.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.CREATE_DATE
     *
     * @param createDate the value for tb_warehouse_position.CREATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.UPDATE_EMP
     *
     * @return the value of tb_warehouse_position.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public String getUpdateEmp() {
        return updateEmp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.UPDATE_EMP
     *
     * @param updateEmp the value for tb_warehouse_position.UPDATE_EMP
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp == null ? null : updateEmp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.UPDATE_DATE
     *
     * @return the value of tb_warehouse_position.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.UPDATE_DATE
     *
     * @param updateDate the value for tb_warehouse_position.UPDATE_DATE
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_warehouse_position.VERSION
     *
     * @return the value of tb_warehouse_position.VERSION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_warehouse_position.VERSION
     *
     * @param version the value for tb_warehouse_position.VERSION
     *
     * @mbggenerated Tue Oct 09 19:52:33 CST 2018
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}