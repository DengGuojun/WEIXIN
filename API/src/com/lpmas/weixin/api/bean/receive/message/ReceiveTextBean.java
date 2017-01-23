package com.lpmas.weixin.api.bean.receive.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;

public class ReceiveTextBean extends MessageBaseBean {
	// 文本消息内容
	@JacksonXmlProperty(localName = "Content")
	private String content = "";

	public ReceiveTextBean() {
		super(ReceiveMessageTypeConfig.RMT_TEXT);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
