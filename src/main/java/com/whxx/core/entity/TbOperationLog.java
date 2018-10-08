package com.whxx.core.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 操作日志
 * </p>
 *
 * @author PH
 * @since 2018-08-02
 */
public class TbOperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    /**
     * 日志类型
     */
    private String logType;
    /**
     * 日志名称
     */
    private String logName;
    /**
     * 用户id
     */
    private String userid;
    /**
     * 类名称
     */
    private String className;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 是否成功
     */
    private String succeed;
    /**
     * 备注
     */
    private String message;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TbOperationLog{" +
        ", id=" + id +
        ", logType=" + logType +
        ", logName=" + logName +
        ", userid=" + userid +
        ", className=" + className +
        ", method=" + method +
        ", createTime=" + createTime +
        ", succeed=" + succeed +
        ", message=" + message +
        "}";
    }
}
