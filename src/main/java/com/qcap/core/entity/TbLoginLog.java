package com.qcap.core.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 登录记录
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
public class TbLoginLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 日志名称
	 */
	private String logName;
	/**
	 * 管理员id
	 */
	private String userid;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 是否执行成功
	 */
	private String succeed;
	/**
	 * 具体消息
	 */
	private String message;
	/**
	 * 登录ip
	 */
	private String ip;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "TbLoginLog{" + ", id=" + id + ", logName=" + logName + ", userid=" + userid + ", createTime="
				+ createTime + ", succeed=" + succeed + ", message=" + message + ", ip=" + ip + "}";
	}
}
