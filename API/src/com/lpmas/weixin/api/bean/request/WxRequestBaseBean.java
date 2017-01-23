package com.lpmas.weixin.api.bean.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WxRequestBaseBean {
	@JsonIgnore
	protected String appId = "";
	@JsonIgnore
	protected String secret = "";
	@JsonIgnore
	protected String accessToken = "";
	@JsonIgnore
	protected Object data = null;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
