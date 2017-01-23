package com.lpmas.weixin.api.bean.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class GetUserListRequestBean extends WxRequestBaseBean {
	// 第一个拉取的OPENID，不填默认从头开始拉取
	@JsonProperty("next_openid")
	private String nextOpenId = "";

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}
}
