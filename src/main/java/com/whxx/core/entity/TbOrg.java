package com.whxx.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
public class TbOrg implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 组织编码
     */
    private String code;
    /**
     * 组织父编码
     */
    private String parentCode;
    /**
     * 组织所有父编码
     */
    private String fullcodes;
    private String num;
    private String parentNum;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 组织全称
     */
    private String fullnames;
    private Integer isOpen;
    private Integer seq;
    @TableField(fill = FieldFill.INSERT)
    private String createEmp;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private String updateEmp;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    private String status;
    /**
     * 版本号
     */
    @Version
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer version;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode()
    {
        return parentCode;
    }

    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    public String getFullcodes() {
        return fullcodes;
    }

    public void setFullcodes(String fullcodes) {
        this.fullcodes = fullcodes;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getParentNum()
    {
        return parentNum;
    }

    public void setParentNum(String parentNum)
    {
        this.parentNum = parentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullnames() {
        return fullnames;
    }

    public void setFullnames(String fullnames) {
        this.fullnames = fullnames;
    }

    public Integer getIsOpen()
    {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen)
    {
        this.isOpen = isOpen;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public LocalDateTime getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }

    public LocalDateTime getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "TbOrg{" +
            ", id=" + id +
            ", code=" + code +
            ", parentCode=" + parentCode +
            ", fullcodes=" + fullcodes +
            ", num=" + num +
            ", parentNum=" + parentNum +
            ", name=" + name +
            ", fullnames=" + fullnames +
            ", isOpen=" + isOpen +
            ", seq=" + seq +
            ", createEmp=" + createEmp +
            ", createTime=" + createTime +
            ", updateEmp=" + updateEmp +
            ", updateTime=" + updateTime +
            ", status=" + status +
            ", version=" + version +
            "}";
    }
}
