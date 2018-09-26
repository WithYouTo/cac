package com.qcap.core.model;

import java.io.Serializable;

public class ResParams implements Serializable {

    private static final long serialVersionUID = 138505707379184683L;

    private Integer code;

    private String desc;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResParams newInstance(Integer code, String desc, Object data){
        ResParams restParams = new ResParams();
        restParams.setCode(code);
        restParams.setDesc(desc);
        restParams.setData(data);
        return restParams;
    }
}
