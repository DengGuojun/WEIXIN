package com.lpmas.weixin.api.bean.response.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class SendMassMessageResponseBean extends WxResponseBaseBean {
	// 消息ID
	@JsonProperty("msg_id")
	private long msgId;
	@JsonProperty("msg_data_id")
	private long msgDataid;

	public long getMsgId() {
		return msgId;
	}

	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public long getMsgDataid() {
		return msgDataid;
	}

	public void setMsgDataid(long msgDataid) {
		this.msgDataid = msgDataid;
	}
}
