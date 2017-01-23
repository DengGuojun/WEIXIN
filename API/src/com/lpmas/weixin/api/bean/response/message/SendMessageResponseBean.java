package com.lpmas.weixin.api.bean.response.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class SendMessageResponseBean extends WxResponseBaseBean{	
	//消息ID
	@JsonProperty("msg_id")
	private Long msgId;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
}
