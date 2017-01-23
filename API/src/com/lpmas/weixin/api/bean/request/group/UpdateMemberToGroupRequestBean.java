package com.lpmas.weixin.api.bean.request.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UpdateMemberToGroupRequestBean extends WxRequestBaseBean {
	@JsonProperty("openid")
	private String openId;
	@JsonProperty("to_groupid")
	private int toGroupId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getToGroupId() {
		return toGroupId;
	}

	public void setToGroupId(int toGroupId) {
		this.toGroupId = toGroupId;
	}
}
