package com.lpmas.weixin.api.bean.response.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetMassMessageStatusResponseBean extends WxResponseBaseBean {

	// 群发消息后返回的消息id
	@JsonProperty("msg_id")
	private Long msgId;

	// 消息发送后的状态，SEND_SUCCESS表示发送成功
	@JsonProperty("msg_status")
	private String msgStatus;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
}
