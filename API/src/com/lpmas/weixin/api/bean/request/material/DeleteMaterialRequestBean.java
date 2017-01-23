package com.lpmas.weixin.api.bean.request.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class DeleteMaterialRequestBean extends WxRequestBaseBean {
	@JsonProperty("media_id")
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
