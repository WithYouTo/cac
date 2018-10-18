package com.qcap.core.entity;

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
 * @since 2018-10-18
 */
@TableName("tb_user_info")
public class TbUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userInfoId;
    private String userId;
    private String userName;
    private String workNo;
    private String flower;
    private String winterClo;
    private String summerClo;
    private String workNoCard;
    private String tape;
    private String goodCapKey;
    private String livingKey;
    private String enterNo;
    private String passNo;
    private String doorOpen;
    private String equipUse;
    private String passArea;
    private String evaluate;
    private String cardType;
    private String cardNo;
    private String gender;
    private Date birth;
    private String marriageSit;
    private Date workDate;
    private String mobile;
    private String email;
    private String address;
    private String emerMobile;
    private String emerPerson;
    private String remark;
    private String createEmp;
    private Date createTime;
    private String updateEmp;
    private Date updateTime;
    private String version;


    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }

    public String getFlower() {
        return flower;
    }

    public void setFlower(String flower) {
        this.flower = flower;
    }

    public String getWinterClo() {
        return winterClo;
    }

    public void setWinterClo(String winterClo) {
        this.winterClo = winterClo;
    }

    public String getSummerClo() {
        return summerClo;
    }

    public void setSummerClo(String summerClo) {
        this.summerClo = summerClo;
    }

    public String getWorkNoCard() {
        return workNoCard;
    }

    public void setWorkNoCard(String workNoCard) {
        this.workNoCard = workNoCard;
    }

    public String getTape() {
        return tape;
    }

    public void setTape(String tape) {
        this.tape = tape;
    }

    public String getGoodCapKey() {
        return goodCapKey;
    }

    public void setGoodCapKey(String goodCapKey) {
        this.goodCapKey = goodCapKey;
    }

    public String getLivingKey() {
        return livingKey;
    }

    public void setLivingKey(String livingKey) {
        this.livingKey = livingKey;
    }

    public String getEnterNo() {
        return enterNo;
    }

    public void setEnterNo(String enterNo) {
        this.enterNo = enterNo;
    }

    public String getPassNo() {
        return passNo;
    }

    public void setPassNo(String passNo) {
        this.passNo = passNo;
    }

    public String getDoorOpen() {
        return doorOpen;
    }

    public void setDoorOpen(String doorOpen) {
        this.doorOpen = doorOpen;
    }

    public String getEquipUse() {
        return equipUse;
    }

    public void setEquipUse(String equipUse) {
        this.equipUse = equipUse;
    }

    public String getPassArea() {
        return passArea;
    }

    public void setPassArea(String passArea) {
        this.passArea = passArea;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public String getMarriageSit() {
        return marriageSit;
    }

    public void setMarriageSit(String marriageSit) {
        this.marriageSit = marriageSit;
    }



    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmerMobile() {
        return emerMobile;
    }

    public void setEmerMobile(String emerMobile) {
        this.emerMobile = emerMobile;
    }

    public String getEmerPerson() {
        return emerPerson;
    }

    public void setEmerPerson(String emerPerson) {
        this.emerPerson = emerPerson;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "TbUserInfo{" +
        ", userInfoId=" + userInfoId +
        ", userId=" + userId +
        ", userName=" + userName +
        ", workNo=" + workNo +
        ", flower=" + flower +
        ", winterClo=" + winterClo +
        ", summerClo=" + summerClo +
        ", workNoCard=" + workNoCard +
        ", tape=" + tape +
        ", goodCapKey=" + goodCapKey +
        ", livingKey=" + livingKey +
        ", enterNo=" + enterNo +
        ", passNo=" + passNo +
        ", doorOpen=" + doorOpen +
        ", equipUse=" + equipUse +
        ", passArea=" + passArea +
        ", evaluate=" + evaluate +
        ", cardType=" + cardType +
        ", cardNo=" + cardNo +
        ", gender=" + gender +
        ", birth=" + birth +
        ", marriageSit=" + marriageSit +
        ", workDate=" + workDate +
        ", mobile=" + mobile +
        ", email=" + email +
        ", address=" + address +
        ", emerMobile=" + emerMobile +
        ", emerPerson=" + emerPerson +
        ", remark=" + remark +
        ", createEmp=" + createEmp +
        ", createTime=" + createTime +
        ", updateEmp=" + updateEmp +
        ", updateTime=" + updateTime +
        ", version=" + version +
        "}";
    }
}
