package com.lpmas.weixin.api.bean.response.group;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupInfoBean {
	@JsonProperty("id")
	private int groupId;
	@JsonProperty("name")
	private String groupName;
	@JsonProperty("count")
	private int groupCount = 0;

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

	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}
}
