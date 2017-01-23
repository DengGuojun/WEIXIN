package com.lpmas.weixin.bean;

import java.sql.Timestamp;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;

public class QrcodeInfoBean extends MongoDBDocumentBean<String> {

	private int accountId = 0;
	private String actionName = "";
	private int sceneId = 0;
	private String sceneStr = "";
	private String appSceneId = "";
	private String ticket = "";
	private String encodeTicket = "";
	private int expireSeconds = 0;
	private String url;
	private Timestamp createTime;
	private String qrcodeType = "";
	private String callBackQueue = "";

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}

	public String getSceneStr() {
		return sceneStr;
	}

	public void setSceneStr(String sceneStr) {
		this.sceneStr = sceneStr;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getAppSceneId() {
		return appSceneId;
	}

	public void setAppSceneId(String appSceneId) {
		this.appSceneId = appSceneId;
	}

	public String getEncodeTicket() {
		return encodeTicket;
	}

	public void setEncodeTicket(String encodeTicket) {
		this.encodeTicket = encodeTicket;
	}

	public String getQrcodeType() {
		return qrcodeType;
	}

	public void setQrcodeType(String qrcodeType) {
		this.qrcodeType = qrcodeType;
	}

	public String getCallBackQueue() {
		return callBackQueue;
	}

	public void setCallBackQueue(String callBackQueue) {
		this.callBackQueue = callBackQueue;
	}

}
