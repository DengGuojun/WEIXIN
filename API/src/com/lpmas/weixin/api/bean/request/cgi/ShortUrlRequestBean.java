package com.lpmas.weixin.api.bean.request.cgi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class ShortUrlRequestBean extends WxRequestBaseBean{

	// 此处填long2short，代表长链接转短链接 不final怕接口变化
	private String action = "long2short";

	// 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url
	@JsonProperty("long_url")
	private String longUrl = "";

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
}
