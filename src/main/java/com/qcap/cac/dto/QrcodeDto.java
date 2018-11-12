package com.qcap.cac.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class QrcodeDto {

	@ApiModelProperty(value = "主键")
	private String id;

	@ApiModelProperty(value = "二维码类型")
	private String qrcodeType;

	@ApiModelProperty(value = "编码")
	private String code;

	@ApiModelProperty(value = "说明")
	private String name;

	@ApiModelProperty(value = "二维码地址")
	private String url;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQrcodeType() {
		return qrcodeType;
	}

	public void setQrcodeType(String qrcodeType) {
		this.qrcodeType = qrcodeType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "QrcodeDto [id=" + id + ", qrcodeType=" + qrcodeType + ", code=" + code + ", name=" + name + ", url="
				+ url + "]";
	}

}