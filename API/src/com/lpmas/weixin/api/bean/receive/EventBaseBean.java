package com.lpmas.weixin.api.bean.receive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventBaseBean extends ReceiveBaseBean {

	// 事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	@JsonProperty("Event")
	private String event;

	public EventBaseBean() {
	}

	public EventBaseBean(String event) {
		this.event = event;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
