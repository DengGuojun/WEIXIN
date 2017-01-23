package com.lpmas.weixin.api.bean.response.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class AddMaterialResponseBean extends WxResponseBaseBean {
	@JsonProperty("media_id")
	private String mediaId;
	@JsonProperty("url")
	private String mediaUrl;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
}
