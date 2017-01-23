package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;

public class SendPictureFromAlbumEventBean extends EventBaseBean {

	// 事件KEY值，由开发者在创建菜单时设定
	@JacksonXmlProperty(localName = "EventKey")
	private String eventKey = "";
	@JacksonXmlProperty(localName = "SendPicsInfo")
	private SendPictureInfoBean sendPictureInfo = new SendPictureInfoBean();
	@JacksonXmlProperty(localName = "AgentID")
	private String agentId;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public SendPictureInfoBean getSendPictureInfo() {
		return sendPictureInfo;
	}

	public void setSendPictureInfo(SendPictureInfoBean sendPictureInfo) {
		this.sendPictureInfo = sendPictureInfo;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
}
