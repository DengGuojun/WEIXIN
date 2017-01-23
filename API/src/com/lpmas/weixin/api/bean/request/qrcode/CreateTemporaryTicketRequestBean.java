package com.lpmas.weixin.api.bean.request.qrcode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateTemporaryTicketRequestBean extends CreateTicketRequestBaseBean {
	@JsonProperty("expire_seconds")
	private int expireSeconds;

	@JsonIgnore
	private int sceneId;

	public CreateTemporaryTicketRequestBean() {
		actionName = "QR_SCENE";
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}
}
