package com.lpmas.weixin.api.bean.request.qrcode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateTicketBySceneStrRequestBean extends CreateTicketRequestBaseBean {
	@JsonIgnore
	private String sceneStr;

	public CreateTicketBySceneStrRequestBean() {
		actionName = "QR_LIMIT_STR_SCENE";
	}

	public String getSceneStr() {
		return sceneStr;
	}

	public void setSceneStr(String sceneStr) {
		this.sceneStr = sceneStr;
	}
}
