package com.lpmas.weixin.api.bean.request.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class CreateGroupRequestBean extends WxRequestBaseBean {
	@JsonProperty("name")
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}
