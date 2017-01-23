package com.lpmas.weixin.api.bean.receive;

public class ReceiveDecryptBean {
	
	//密文中获取的appId
	private String appId;
	
	//密文中获取的明文信息
	private String decryptXml;
	
	public ReceiveDecryptBean() {}
	
	public ReceiveDecryptBean(String appId, String decryptXml) {
		this.appId = appId;
		this.decryptXml = decryptXml;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getDecryptXml() {
		return decryptXml;
	}

	public void setDecryptXml(String decryptXml) {
		this.decryptXml = decryptXml;
	}
	
	
}
