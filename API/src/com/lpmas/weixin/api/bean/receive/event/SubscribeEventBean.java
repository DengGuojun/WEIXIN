package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class SubscribeEventBean extends EventBaseBean {
	@JacksonXmlProperty(localName = "EventKey")
	private String eventKey;
	@JacksonXmlProperty(localName = "Ticket")
	private String ticket;

	public SubscribeEventBean() {
		super(ReceiveEventConfig.EVENT_SUBSCRIBE);
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
