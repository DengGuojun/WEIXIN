package com.lpmas.weixin.api.bean.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class RefreshAuthorizeAccessTokenResponseBean extends WxResponseBaseBean {

	// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	@JsonProperty("access_token")
	private String accessToken;

	// access_token接口调用凭证超时时间，单位（秒）
	@JsonProperty("expires_in")
	private Integer expiresIn;

	// 用户刷新access_token
	@JsonProperty("refreshToken")
	private String refresh_token;

	// 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	@JsonProperty("openId")
	private String openid;

	// 用户授权的作用域，使用逗号（,）分隔
	private String scope;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Integer getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
}
