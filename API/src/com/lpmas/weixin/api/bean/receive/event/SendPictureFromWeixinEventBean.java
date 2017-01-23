package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class SendPictureFromWeixinEventBean extends EventBaseBean {

	// 事件KEY值，由开发者在创建菜单时设定
	@JacksonXmlProperty(localName = "EventKey")
	private String eventKey;

	@JacksonXmlProperty(localName = "AgentID")
	private String agentId;

	// 扫描信息
	@JacksonXmlProperty(localName = "SendPicsInfo")
	private SendPictureInfoBean sendPictureInfo = new SendPictureInfoBean();

	public SendPictureFromWeixinEventBean() {
		super(ReceiveEventConfig.EVENT_SEND_PICTURE_FROM_WEIXIN);
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public SendPictureInfoBean getSendPictureInfo() {
		return sendPictureInfo;
	}

	public void setSendPictureInfo(SendPictureInfoBean sendPictureInfo) {
		this.sendPictureInfo = sendPictureInfo;
	}
}
