package com.lpmas.weixin.api.bean.request.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class GetMassMessageStatusRequestBean extends WxRequestBaseBean {
	@JsonProperty("msg_id")
	private String msgId = "";

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
