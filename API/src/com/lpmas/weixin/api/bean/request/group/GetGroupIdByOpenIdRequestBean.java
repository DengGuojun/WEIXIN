package com.lpmas.weixin.api.bean.request.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class GetGroupIdByOpenIdRequestBean extends WxRequestBaseBean {
	@JsonProperty("openid")
	private String openId = "";

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
