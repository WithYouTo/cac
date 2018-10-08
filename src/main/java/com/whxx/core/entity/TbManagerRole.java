package com.whxx.core.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author PH
 * @since 2018-08-02
 */
public class TbManagerRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 外键（tb_role）
     */
    private String roleId;
    /**
     * 外键（tb_manager）
     */
    private String managerId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "TbManagerRole{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", managerId=" + managerId +
        "}";
    }
}
