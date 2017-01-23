package com.lpmas.weixin.api.bean.receive;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MessageBaseBean extends ReceiveBaseBean {

	// 消息id，64位整型 MsgId
	@JacksonXmlProperty(localName = "MsgId")
	private String msgId = "";

	public MessageBaseBean() {
	}

	public MessageBaseBean(String msgType) {
		super(msgType);
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

}
