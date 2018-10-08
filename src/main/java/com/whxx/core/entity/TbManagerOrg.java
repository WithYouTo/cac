package com.whxx.core.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
public class TbManagerOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String orgId;
    private String managerId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "TbManagerOrg{" +
        ", id=" + id +
        ", orgId=" + orgId +
        ", managerId=" + managerId +
        "}";
    }
}
