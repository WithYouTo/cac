package com.qcap.cac.dto;

import java.util.List;

public class BatchUpdateQrcodeDto {

	private List<QrcodeDto> qrcodeList;

	public List<QrcodeDto> getQrcodeList() {
		return qrcodeList;
	}

	public void setQrcodeList(List<QrcodeDto> qrcodeList) {
		this.qrcodeList = qrcodeList;
	}

}
