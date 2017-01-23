package com.lpmas.weixin.api.bean.response.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class CreateGroupResponseBean extends WxResponseBaseBean {
	@JsonProperty("group")
	private GroupInfoBean groupInfo;

	public GroupInfoBean getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(GroupInfoBean groupInfo) {
		this.groupInfo = groupInfo;
	}
}
