package com.lpmas.weixin.api.bean.receive;

public class MessageConfigBean {
	private String token;
	private String appId;
	private String cryptoKey;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCryptoKey() {
		return cryptoKey;
	}

	public void setCryptoKey(String cryptoKey) {
		this.cryptoKey = cryptoKey;
	}

}
