package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class ScanCodePushEventBean extends EventBaseBean {

	public ScanCodePushEventBean() {
		super(ReceiveEventConfig.EVENT_SCAN_CODE_PUSH);
	}

	// 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	@JacksonXmlProperty(localName = "EventKey")
	private String eventKey;
	@JacksonXmlProperty(localName = "AgentID")
	private String agentId;
	@JacksonXmlProperty(localName = "ScanCodeInfo")
	private ScanCodeInfoBean scanCodeInfo = new ScanCodeInfoBean();

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

	public ScanCodeInfoBean getScanCodeInfo() {
		return scanCodeInfo;
	}

	public void setScanCodeInfo(ScanCodeInfoBean scanCodeInfo) {
		this.scanCodeInfo = scanCodeInfo;
	}
}
