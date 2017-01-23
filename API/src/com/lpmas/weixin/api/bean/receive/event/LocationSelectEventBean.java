package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class LocationSelectEventBean extends EventBaseBean {

	// 事件KEY值，由开发者在创建菜单时设定
	@JacksonXmlProperty(localName = "EventKey")
	private String eventKey = "";
	@JacksonXmlProperty(localName = "AgentID")
	private String agentId;
	// 发送的位置信息
	@JacksonXmlProperty(localName = "SendLocationInfo")
	private LocationInfoBean sendLocationInfo = new LocationInfoBean();

	public LocationSelectEventBean() {
		super(ReceiveEventConfig.EVENT_LOCATION_SELECT);
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

	public LocationInfoBean getSendLocationInfo() {
		return sendLocationInfo;
	}

	public void setSendLocationInfo(LocationInfoBean sendLocationInfo) {
		this.sendLocationInfo = sendLocationInfo;
	}
}
