package com.lpmas.weixin.api.bean.request.qrcode;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class CreateTicketRequestBaseBean extends WxRequestBaseBean {
	@JsonProperty("action_name")
	protected String actionName;
	@JsonProperty("action_info")
	private Object actionInfo;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Object getActionInfo() {
		return actionInfo;
	}

	public void setActionInfo(Object actionInfo) {
		this.actionInfo = actionInfo;
	}
}
