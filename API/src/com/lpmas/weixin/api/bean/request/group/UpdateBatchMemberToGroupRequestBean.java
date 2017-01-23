package com.lpmas.weixin.api.bean.request.group;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UpdateBatchMemberToGroupRequestBean extends WxRequestBaseBean {
	@JsonProperty("openid_list")
	private List<String> openIdList = new ArrayList<String>();
	@JsonProperty("to_groupid")
	private int toGroupId;

	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

	public int getToGroupId() {
		return toGroupId;
	}

	public void setToGroupId(int toGroupId) {
		this.toGroupId = toGroupId;
	}
}
