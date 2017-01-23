package com.lpmas.weixin.api.bean.request.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UpdateGroupRequestBean extends WxRequestBaseBean {
	@JsonProperty("id")
	private int groupId;
	@JsonProperty("name")
	private String groupName;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
