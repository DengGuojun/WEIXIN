package com.lpmas.weixin.api.bean.request.auth;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class GetAuthorizeUserInfoRequestBean extends WxRequestBaseBean {
	// 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	private String accessToken = "";
	// 用户标识
	private String openId = "";

	// 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	private String lang = "";

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
}
