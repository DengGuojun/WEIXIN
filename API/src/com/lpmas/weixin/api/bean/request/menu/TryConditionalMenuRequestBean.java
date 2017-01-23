package com.lpmas.weixin.api.bean.request.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class TryConditionalMenuRequestBean extends WxRequestBaseBean {
	// user_id可以是粉丝的OpenID，也可以是粉丝的微信号
	@JsonProperty("user_id")
	private String openId = "";

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
