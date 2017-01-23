package com.lpmas.weixin.api.bean.request.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public abstract class PreviewMessageRequestBean extends WxRequestBaseBean {
	// 接收消息用户对应该公众号的openid
	@JsonProperty("touser")
	private String openId = "";
	@JsonProperty("towxname")
	private String weixinName = "";
	// 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video
	@JsonProperty("msgtype")
	private String msgType = "";

	public PreviewMessageRequestBean() {

	}

	public PreviewMessageRequestBean(String msgType) {
		this.msgType = msgType;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWeixinName() {
		return weixinName;
	}

	public void setWeixinName(String weixinName) {
		this.weixinName = weixinName;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
}
