package com.lpmas.weixin.api.bean.request.message;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public abstract class SendCustomServiceMessageRequestBean extends WxRequestBaseBean {
	@JsonIgnore
	private String accout = "";

	// 普通用户openid
	@JsonProperty("touser")
	private String openId = "";

	// 消息类型
	@JsonProperty("msgtype")
	private String msgType = "";

	@JsonProperty("customservice")
	private Map<String, Object> accountMap;

	public SendCustomServiceMessageRequestBean() {
	}

	public SendCustomServiceMessageRequestBean(String msgType) {
		this.msgType = msgType;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Map<String, Object> getAccountMap() {
		accountMap = new HashMap<String, Object>();
		accountMap.put("kf_account", getAccout());
		return accountMap;
	}

	public void setAccountMap(Map<String, Object> accountMap) {
		this.accountMap = accountMap;
	}
}
