package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class ScanEventBean extends EventBaseBean {

	// 事件KEY值，qrscene_为前缀，后面为二维码的参数值
	@JacksonXmlProperty(localName = "EventKey")
	private String eventKey = "";

	// 二维码的ticket，可用来换取二维码图片
	@JacksonXmlProperty(localName = "Ticket")
	private String ticket = "";

	public ScanEventBean() {
		super(ReceiveEventConfig.EVENT_SCAN);
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
