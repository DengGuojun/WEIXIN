package com.lpmas.weixin.api.bean.material;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MaterialInfoBean {
	@JsonProperty("media_id")
	private String mediaId;
	@JsonProperty("name")
	private String mediaName;
	@JsonProperty("update_time")
	private int updateTime;
	@JsonProperty("url")
	private String mediaUrl;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public int getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
}
