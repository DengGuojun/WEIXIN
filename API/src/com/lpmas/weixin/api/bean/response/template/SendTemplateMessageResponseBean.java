package com.lpmas.weixin.api.bean.response.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class SendTemplateMessageResponseBean extends WxResponseBaseBean {
	@JsonProperty("msgid")
	private Long messageId;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
}
