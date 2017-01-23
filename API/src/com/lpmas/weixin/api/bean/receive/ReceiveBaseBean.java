package com.lpmas.weixin.api.bean.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReceiveBaseBean {

	// 开发者微信号 ToUserName
	@JsonProperty("ToUserName")
	private String toUserName = "";

	// 发送方帐号（一个OpenID） FromUserName
	@JsonProperty("FromUserName")
	private String fromUserName = "";

	// 消息创建时间 （整型） CreateTime
	@JsonProperty("CreateTime")
	private long createTime;

	// 消息类型 MsgType
	@JsonProperty("MsgType")
	private String msgType = "";

	public ReceiveBaseBean() {
	}

	public ReceiveBaseBean(String msgType) {
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
