package com.lpmas.weixin.api.bean.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UpdateUserRemarkRequestBean extends WxRequestBaseBean {
	// 用户标识
	@JsonProperty("openid")
	private String openId = "";

	// 新的备注名，长度必须小于30字符
	private String remark = "";

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
