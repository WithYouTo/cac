package com.whxx.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author PH
 * @since 2018-07-30
 */
public class TbMenu implements Serializable
{

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;
    /**
     * 编号
     */
    private String num;
    private String parentNum;
    /**
     * 菜单编号
     */
    private String code;
    /**
     * 菜单父编号
     */
    private String parentCode;
    /**
     * 当前菜单的所有父菜单编号
     */
    private String fullCodes;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * url地址
     */
    private String url;
    /**
     * 菜单排序号
     */
    private Integer seq;
    /**
     * 菜单层级
     */
    private Integer level;
    /**
     * 是否是菜单（1：是  0：不是）
     */
    private Integer isMenu;
    /**
     * 备注
     */
    private String remark;
    /**
     * 菜单状态 :  1:启用   0:不启用
     */
    private Integer status;
    /**
     * 是否打开:    1:打开   0:不打开
     */
    private Integer isOpen;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createEmp;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateEmp;
    /**
     * 乐观锁保留字段
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

    public String getFullCodes() {
        return fullCodes;
    }

    public void setFullCodes(String fullCodes) {
        this.fullCodes = fullCodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getIsMenu()
    {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu)
    {
        this.isMenu = isMenu;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getIsOpen()
    {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen)
    {
        this.isOpen = isOpen;
    }

    public LocalDateTime getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime)
    {
        this.createTime = createTime;
    }

    public String getCreateEmp() {
        return createEmp;
    }

    public void setCreateEmp(String createEmp) {
        this.createEmp = createEmp;
    }

    public LocalDateTime getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getUpdateEmp() {
        return updateEmp;
    }

    public void setUpdateEmp(String updateEmp) {
        this.updateEmp = updateEmp;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "TbMenu{" +
            ", id=" + id +
            ", num=" + num +
            ", parentNum=" + parentNum +
            ", code=" + code +
            ", parentCode=" + parentCode +
            ", fullCodes=" + fullCodes +
            ", name=" + name +
            ", icon=" + icon +
            ", url=" + url +
            ", seq=" + seq +
            ", level=" + level +
            ", isMenu=" + isMenu +
            ", remark=" + remark +
            ", status=" + status +
            ", isOpen=" + isOpen +
            ", createTime=" + createTime +
            ", createEmp=" + createEmp +
            ", updateTime=" + updateTime +
            ", updateEmp=" + updateEmp +
            ", version=" + version +
            "}";
    }
}
