package com.lpmas.weixin.api.bean.response.cgi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetAccessTokenResponseBean extends WxResponseBaseBean {
	@JsonProperty("access_token")
	private String accessToken = "";
	@JsonProperty("expires_in")
	private int expiresIn = 0;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}
