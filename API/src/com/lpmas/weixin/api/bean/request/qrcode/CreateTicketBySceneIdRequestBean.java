package com.lpmas.weixin.api.bean.request.qrcode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CreateTicketBySceneIdRequestBean extends CreateTicketRequestBaseBean {
	@JsonIgnore
	private int sceneId;

	public CreateTicketBySceneIdRequestBean() {
		actionName = "QR_LIMIT_SCENE";
	}

	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}
}
