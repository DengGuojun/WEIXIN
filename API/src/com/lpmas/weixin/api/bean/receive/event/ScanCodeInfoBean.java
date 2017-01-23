package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ScanCodeInfoBean {
	// 扫描类型，一般是qrcode
	@JacksonXmlProperty(localName = "ScanType")
	private String scanType;

	// 扫描结果，即二维码对应的字符串信息
	@JacksonXmlProperty(localName = "ScanResult")
	private String scanResult;

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public String getScanResult() {
		return scanResult;
	}

	public void setScanResult(String scanResult) {
		this.scanResult = scanResult;
	}
}
