package com.lpmas.weixin.bean;

import org.bson.types.ObjectId;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;

public class ReceiveMessageBean extends MongoDBDocumentBean<ObjectId> {

	private int accountId = 0;
	private String msgId;
	private String msgType;
	private String fromUserName;
	private long createTime;
	private String messageContentClassName = "";
	private String messageContent;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
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

	public String getMessageContentClassName() {
		return messageContentClassName;
	}

	public void setMessageContentClassName(String messageContentClassName) {
		this.messageContentClassName = messageContentClassName;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

}
