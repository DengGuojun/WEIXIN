package com.lpmas.weixin.api.bean.request.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class DeleteMassMessageRequestBean extends WxRequestBaseBean {
	@JsonProperty("msg_id")
	private Long msgId;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
}
