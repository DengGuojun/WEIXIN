package com.lpmas.weixin.api.bean.request.auth;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class RefreshAuthorizeAccessTokenRequestBean extends WxRequestBaseBean {

	// 填写通过access_token获取到的refresh_token参数
	private String refreshToken = "";

	// 填写为authorization_code
	private String grantType = "refresh_token";

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
}
