package com.qcap.cac.exception;

/**
 * 公共异常类
 *
 * @version 1.0.0
 * @Author Wuhuakeji_zzc
 * @create 2018-06-11 10:04
 **/

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = -7410425711834358349L;

	private int code;

	public BaseException(String msg) {
		super(msg);
	}

	public BaseException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
