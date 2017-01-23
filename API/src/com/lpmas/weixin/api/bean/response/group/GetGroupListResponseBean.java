package com.lpmas.weixin.api.bean.response.group;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetGroupListResponseBean extends WxResponseBaseBean {

	@JsonProperty("groups")
	private List<GroupInfoBean> groupList = new ArrayList<GroupInfoBean>();

	public List<GroupInfoBean> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<GroupInfoBean> groupList) {
		this.groupList = groupList;
	}
}
