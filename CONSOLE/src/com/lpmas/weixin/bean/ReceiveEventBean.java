package com.lpmas.weixin.bean;

import org.bson.types.ObjectId;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;

public class ReceiveEventBean extends MongoDBDocumentBean<ObjectId> {

	private int accountId = 0;
	private String event;
	private String msgType;
	private String fromUserName;
	private long createTime;
	private String eventContentClassName = "";
	private String eventContent;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
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

	public String getEventContentClassName() {
		return eventContentClassName;
	}

	public void setEventContentClassName(String eventContentClassName) {
		this.eventContentClassName = eventContentClassName;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

}
