package com.qcap.cac.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author huangxiang
 * @since 2018-10-15
 */
@TableName("tb_equip")
public class TbEquip implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String equipId;
    /**
     * 项目编号
     */
    private String projectCode;
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
     * 设备类型
     */
    private String equipType;
    /**
     * 设备全名
     */
    private String equipFullName;
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
     * 责任人姓名
     */
    private String responseName;
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
     * 停放地点
     */
    private String stopPlace;
    /**
     * 存放区域
     */
    private String storeArea;
    /**
     * 充电点位
     */
    private String chargePlace;
    /**
     * 充电区域
     */
    private String chargeArea;
    /**
     * 生产厂家
     */
    private String manufact;
    /**
     * 经销商编号
     */
    private String agenceNo;
    /**
     * 经销商
     */
    private String agenceName;
    /**
     * 联系人
     */
    private String relateName;
    /**
     * 联系方式
     */
    private String relateMobile;
    /**
     * 使用年限
     */
    private String lifeCycle;
    /**
     * 日常保养间隔
     */
    private String dailyMaint;
    /**
     * 整机维保间隔
     */
    private String maintCycle;
    /**
     * 最近维保时间
     */
    private Date latestMaintTime;
    /**
     * 下次维保时间
     */
    private Date nextMaintTime;
    /**
     * 保修期
     */
    private String warranty;
    /**
     * 购买日期
     */
    private Date buyTime;
    /**
     * 启用日期
     */
    private Date startUseTime;
    /**
     * 报废日期
     */
    private Date scrapTime;
    /**
     * 报废原因
     */
    private String scrapReason;
    /**
     * 基本巡查要求
     */
    private String baseRequire;
    /**
     * 注意事项
     */
    private String attention;
    /**
     * 图片地址
     */
    private String imgUrl;
    /**
     * 文件地址
     */
    private String fileUrls;
    /**
     * 文件地址
     */
    private String fileNames;
    /**
     * 设备状态
     */
    private String equipState;
    /**
     * 设备工作状态
     */
    private String equipWorkState;
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
    private Date createDate;
    /**
     * 修改人
     */
    private String updateEmp;
    /**
     * 修改时间
     */
    private Date updateDate;
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

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
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

    public String getEquipType() {
        return equipType;
    }

    public void setEquipType(String equipType) {
        this.equipType = equipType;
    }

    public String getEquipFullName() {
        return equipFullName;
    }

    public void setEquipFullName(String equipFullName) {
        this.equipFullName = equipFullName;
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

    public String getResponseName() {
        return responseName;
    }

    public void setResponseName(String responseName) {
        this.responseName = responseName;
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

    public String getStopPlace() {
        return stopPlace;
    }

    public void setStopPlace(String stopPlace) {
        this.stopPlace = stopPlace;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public String getChargePlace() {
        return chargePlace;
    }

    public void setChargePlace(String chargePlace) {
        this.chargePlace = chargePlace;
    }

    public String getChargeArea() {
        return chargeArea;
    }

    public void setChargeArea(String chargeArea) {
        this.chargeArea = chargeArea;
    }

    public String getManufact() {
        return manufact;
    }

    public void setManufact(String manufact) {
        this.manufact = manufact;
    }

    public String getAgenceNo() {
        return agenceNo;
    }

    public void setAgenceNo(String agenceNo) {
        this.agenceNo = agenceNo;
    }

    public String getAgenceName() {
        return agenceName;
    }

    public void setAgenceName(String agenceName) {
        this.agenceName = agenceName;
    }

    public String getRelateName() {
        return relateName;
    }

    public void setRelateName(String relateName) {
        this.relateName = relateName;
    }

    public String getRelateMobile() {
        return relateMobile;
    }

    public void setRelateMobile(String relateMobile) {
        this.relateMobile = relateMobile;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getDailyMaint() {
        return dailyMaint;
    }

    public void setDailyMaint(String dailyMaint) {
        this.dailyMaint = dailyMaint;
    }

    public String getMaintCycle() {
        return maintCycle;
    }

    public void setMaintCycle(String maintCycle) {
        this.maintCycle = maintCycle;
    }

    public Date getLatestMaintTime() {
        return latestMaintTime;
    }

    public void setLatestMaintTime(Date latestMaintTime) {
        this.latestMaintTime = latestMaintTime;
    }

    public Date getNextMaintTime() {
        return nextMaintTime;
    }

    public void setNextMaintTime(Date nextMaintTime) {
        this.nextMaintTime = nextMaintTime;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getStartUseTime() {
        return startUseTime;
    }

    public void setStartUseTime(Date startUseTime) {
        this.startUseTime = startUseTime;
    }

    public Date getScrapTime() {
        return scrapTime;
    }

    public void setScrapTime(Date scrapTime) {
        this.scrapTime = scrapTime;
    }

    public String getScrapReason() {
        return scrapReason;
    }

    public void setScrapReason(String scrapReason) {
        this.scrapReason = scrapReason;
    }

    public String getBaseRequire() {
        return baseRequire;
    }

    public void setBaseRequire(String baseRequire) {
        this.baseRequire = baseRequire;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(String fileUrls) {
        this.fileUrls = fileUrls;
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public String getEquipState() {
        return equipState;
    }

    public void setEquipState(String equipState) {
        this.equipState = equipState;
    }

    public String getEquipWorkState() {
        return equipWorkState;
    }

    public void setEquipWorkState(String equipWorkState) {
        this.equipWorkState = equipWorkState;
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

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
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
                "equipId='" + equipId + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", equipCodeUrl='" + equipCodeUrl + '\'' +
                ", equipName='" + equipName + '\'' +
                ", equipType='" + equipType + '\'' +
                ", equipFullName='" + equipFullName + '\'' +
                ", equipModel='" + equipModel + '\'' +
                ", equipParams='" + equipParams + '\'' +
                ", responseNo='" + responseNo + '\'' +
                ", responseName='" + responseName + '\'' +
                ", buliding='" + buliding + '\'' +
                ", floor='" + floor + '\'' +
                ", area='" + area + '\'' +
                ", room='" + room + '\'' +
                ", roomArea='" + roomArea + '\'' +
                ", equipUse='" + equipUse + '\'' +
                ", useArea='" + useArea + '\'' +
                ", stopPlace='" + stopPlace + '\'' +
                ", storeArea='" + storeArea + '\'' +
                ", chargePlace='" + chargePlace + '\'' +
                ", chargeArea='" + chargeArea + '\'' +
                ", manufact='" + manufact + '\'' +
                ", agenceNo='" + agenceNo + '\'' +
                ", agenceName='" + agenceName + '\'' +
                ", relateName='" + relateName + '\'' +
                ", relateMobile='" + relateMobile + '\'' +
                ", lifeCycle='" + lifeCycle + '\'' +
                ", dailyMaint='" + dailyMaint + '\'' +
                ", maintCycle='" + maintCycle + '\'' +
                ", latestMaintTime=" + latestMaintTime +
                ", nextMaintTime=" + nextMaintTime +
                ", warranty='" + warranty + '\'' +
                ", buyTime=" + buyTime +
                ", startUseTime=" + startUseTime +
                ", scrapTime=" + scrapTime +
                ", scrapReason='" + scrapReason + '\'' +
                ", baseRequire='" + baseRequire + '\'' +
                ", attention='" + attention + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", fileUrls='" + fileUrls + '\'' +
                ", fileNames='" + fileNames + '\'' +
                ", equipState='" + equipState + '\'' +
                ", equipWorkState='" + equipWorkState + '\'' +
                ", remark='" + remark + '\'' +
                ", createEmp='" + createEmp + '\'' +
                ", createDate=" + createDate +
                ", updateEmp='" + updateEmp + '\'' +
                ", updateDate=" + updateDate +
                ", version=" + version +
                '}';
    }
}
