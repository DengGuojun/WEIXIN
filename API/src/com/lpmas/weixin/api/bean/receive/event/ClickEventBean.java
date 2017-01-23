package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class ClickEventBean extends EventBaseBean {

	// 事件KEY值，与自定义菜单接口中KEY值对应
	@JacksonXmlProperty(localName = "EventKey")
	private String eventKey = "";

	public ClickEventBean() {
		super(ReceiveEventConfig.EVENT_CLICK);
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
