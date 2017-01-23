package com.lpmas.weixin.api.bean.request.auth;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class CheckAuthorizeAccessTokenValidRequestBean extends WxRequestBaseBean {

	// 用户的唯一标识
	private String openId = "";

	// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	private String accessToken = "";

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
}
