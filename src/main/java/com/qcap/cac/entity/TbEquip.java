package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-12
 */
@TableName("tb_equip")
public class TbEquip implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String equipId;
    /**
     * 设备编号
     */
    private String equipNo;
    /**
     * 设备二维码地址
     */
    private String equipCodeUrl;
    /**
     * 设备名称
     */
    private String equipName;
    /**
     * 设备全名
     */
    private String equipFullName;
    /**
     * 设备类别
     */
    private String equipType;
    /**
     * 设备型号
     */
    private String equipModel;
    /**
     * 设备参数
     */
    private String equipParams;
    /**
     * 责任人编号
     */
    private String responseNo;
    /**
     * 建筑物
     */
    private String buliding;
    /**
     * 楼层
     */
    private String floor;
    /**
     * 区域
     */
    private String area;
    /**
     * 房间
     */
    private String room;
    /**
     * 房间分区
     */
    private String roomArea;
    /**
     * 设备用途
     */
    private String equipUse;
    /**
     * 使用区域
     */
    private String useArea;
    /**
     * 存放地点
     */
    private String storeArea;
    /**
     * 生产厂家
     */
    private String manufact;
    /**
     * 生命周期
     */
    private String recycle;
    /**
     * 维保周期
     */
    private String maintCycle;
    /**
     * 最近维保时间
     */
    private LocalDate latestMaintTime;
    /**
     * 下次维保时间
     */
    private LocalDate nextMaintTime;
    /**
     * 保修期
     */
    private String maintenPeriod;
    /**
     * 购买日期
     */
    private LocalDateTime buyTime;
    /**
     * 启用日期
     */
    private LocalDateTime startUseTime;
    /**
     * 报废日期
     */
    private LocalDateTime scrapTime;
    /**
     * 报废原因
     */
    private String scrapReason;
    /**
     * 设备状态
     */
    private String equipState;
    /**
     * 备注
     */
    private String remark;
    /**
     * 新增人
     */
    private String createEmp;
    /**
     * 新增时间
     */
    private LocalDateTime createDate;
    /**
     * 修改人
     */
    private String updateEmp;
    /**
     * 修改时间
     */
    private LocalDateTime updateDate;
    /**
     * 版本
     */
    private Integer version;


    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getEquipCodeUrl() {
        return equipCodeUrl;
    }

    public void setEquipCodeUrl(String equipCodeUrl) {
        this.equipCodeUrl = equipCodeUrl;
    }

    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public String getEquipFullName() {
        return equipFullName;
    }

    public void setEquipFullName(String equipFullName) {
        this.equipFullName = equipFullName;
    }

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipModel() {
        return equipModel;
    }

    public void setEquipModel(String equipModel) {
        this.equipModel = equipModel;
    }

    public String getEquipParams() {
        return equipParams;
    }

    public void setEquipParams(String equipParams) {
        this.equipParams = equipParams;
    }

    public String getResponseNo() {
        return responseNo;
    }

    public void setResponseNo(String responseNo) {
        this.responseNo = responseNo;
    }

    public String getBuliding() {
        return buliding;
    }

    public void setBuliding(String buliding) {
        this.buliding = buliding;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoomArea() {
        return roomArea;
    }

    public void setRoomArea(String roomArea) {
        this.roomArea = roomArea;
    }

    public String getEquipUse() {
        return equipUse;
    }

    public void setEquipUse(String equipUse) {
        this.equipUse = equipUse;
    }

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public String getManufact() {
        return manufact;
    }

    public void setManufact(String manufact) {
        this.manufact = manufact;
    }

    public String getRecycle() {
        return recycle;
    }

    public void setRecycle(String recycle) {
        this.recycle = recycle;
    }

    public String getMaintCycle() {
        return maintCycle;
    }

    public void setMaintCycle(String maintCycle) {
        this.maintCycle = maintCycle;
    }

    public LocalDate getLatestMaintTime() {
        return latestMaintTime;
    }

    public void setLatestMaintTime(LocalDate latestMaintTime) {
        this.latestMaintTime = latestMaintTime;
    }

    public LocalDate getNextMaintTime() {
        return nextMaintTime;
    }

    public void setNextMaintTime(LocalDate nextMaintTime) {
        this.nextMaintTime = nextMaintTime;
    }

    public String getMaintenPeriod() {
        return maintenPeriod;
    }

    public void setMaintenPeriod(String maintenPeriod) {
        this.maintenPeriod = maintenPeriod;
    }

    public LocalDateTime getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {
        this.buyTime = buyTime;
    }

    public LocalDateTime getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(LocalDateTime startUseTime) {
        this.startUseTime = startUseTime;
    }

    public LocalDateTime getScrapTime() {
        return scrapTime;
    }

    public void setScrapTime(LocalDateTime scrapTime) {
        this.scrapTime = scrapTime;
    }

    public String getScrapReason() {
        return scrapReason;
    }

    public void setScrapReason(String scrapReason) {
        this.scrapReason = scrapReason;
    }

    public String getEquipState() {
        return equipState;
    }

    public void setEquipState(String equipState) {
        this.equipState = equipState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TbEquip{" +
        ", equipId=" + equipId +
        ", equipNo=" + equipNo +
        ", equipCodeUrl=" + equipCodeUrl +
        ", equipName=" + equipName +
        ", equipFullName=" + equipFullName +
        ", equipType=" + equipType +
        ", equipModel=" + equipModel +
        ", equipParams=" + equipParams +
        ", responseNo=" + responseNo +
        ", buliding=" + buliding +
        ", floor=" + floor +
        ", area=" + area +
        ", room=" + room +
        ", roomArea=" + roomArea +
        ", equipUse=" + equipUse +
        ", useArea=" + useArea +
        ", storeArea=" + storeArea +
        ", manufact=" + manufact +
        ", recycle=" + recycle +
        ", maintCycle=" + maintCycle +
        ", latestMaintTime=" + latestMaintTime +
        ", nextMaintTime=" + nextMaintTime +
        ", maintenPeriod=" + maintenPeriod +
        ", buyTime=" + buyTime +
        ", startUseTime=" + startUseTime +
        ", scrapTime=" + scrapTime +
        ", scrapReason=" + scrapReason +
        ", equipState=" + equipState +
        ", remark=" + remark +
        ", createEmp=" + createEmp +
        ", createDate=" + createDate +
        ", updateEmp=" + updateEmp +
        ", updateDate=" + updateDate +
        ", version=" + version +
        "}";
    }
}
