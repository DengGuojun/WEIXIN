package com.lpmas.weixin.api.bean.receive;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ReplyMessageBaseBean {
	// 接收方帐号（收到的OpenID）
	@JacksonXmlCData
	@JacksonXmlProperty(localName = "ToUserName")
	private String toUserName = "";

	// 开发者微信号
	@JacksonXmlCData
	@JacksonXmlProperty(localName = "FromUserName")
	private String fromUserName = "";

	// 消息创建时间 （整型） CreateTime
	@JacksonXmlProperty(localName = "CreateTime")
	private long createTime = 0;

	// 消息类型 MsgType
	@JacksonXmlCData
	@JacksonXmlProperty(localName = "MsgType")
	private String msgType = "";

	public ReplyMessageBaseBean() {
	}

	public ReplyMessageBaseBean(String msgType) {
		this.msgType = msgType;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

}
