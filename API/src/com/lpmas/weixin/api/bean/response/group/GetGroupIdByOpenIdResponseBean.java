package com.lpmas.weixin.api.bean.response.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetGroupIdByOpenIdResponseBean extends WxResponseBaseBean {
	@JsonProperty("groupid")
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
