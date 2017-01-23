package com.lpmas.weixin.api.bean.request.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

//注意本接口是删除一个用户分组，删除分组后，所有该分组内的用户自动进入默认分组
public class DeleteGroupRequestBean extends WxRequestBaseBean {
	@JsonProperty("id")
	private int groupId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
