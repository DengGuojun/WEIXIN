package com.lpmas.weixin.api.bean.request.message;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public abstract class SendMessageRequestBean extends WxRequestBaseBean {
	@JsonProperty("touser")
	protected List<String> openIdList = new ArrayList<String>();
	@JsonProperty("msgtype")
	protected String msgType = "";

	public SendMessageRequestBean() {
	}

	public SendMessageRequestBean(String msgType) {
		this.msgType = msgType;
	}

	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public void setOpenId(String openId) {
		if (!openIdList.contains(openId)) {
			openIdList.add(openId);
		}
	}
}
