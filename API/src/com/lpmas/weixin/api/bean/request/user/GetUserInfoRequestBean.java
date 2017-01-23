package com.lpmas.weixin.api.bean.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class GetUserInfoRequestBean extends WxRequestBaseBean {
	// 用户标识
	@JsonProperty("openid")
	private String openId = "";
	// 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	@JsonProperty("lang")
	private String language = "zh_CN";

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
