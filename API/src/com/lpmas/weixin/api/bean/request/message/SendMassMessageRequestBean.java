package com.lpmas.weixin.api.bean.request.message;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public abstract class SendMassMessageRequestBean extends WxRequestBaseBean {
	@JsonIgnore
	private boolean isToAll = false;
	@JsonIgnore
	private String tagId = "";
	// 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video
	@JsonProperty("msgtype")
	private String msgType = "";

	@JsonProperty("filter")
	private Map<String, Object> targetMap;

	public SendMassMessageRequestBean() {
	}

	public SendMassMessageRequestBean(String msgType) {
		this.msgType = msgType;
	}

	public boolean isToAll() {
		return isToAll;
	}

	public void setToAll(boolean isToAll) {
		this.isToAll = isToAll;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Map<String, Object> getTargetMap() {
		targetMap = new HashMap<String, Object>();
		targetMap.put("is_to_all", isToAll);
		targetMap.put("tag_id", tagId);
		return targetMap;
	}

	public void setTargetMap(Map<String, Object> targetMap) {
		this.targetMap = targetMap;
	}
}
