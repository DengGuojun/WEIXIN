package com.lpmas.weixin.api.bean.response.material;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsListResponseBean {
	@JsonProperty("media_id")
	private String mediaId;
	@JsonProperty("content")
	private NewsContentResponseBean content;
	@JsonProperty("update_time")
	private int updateTime;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public NewsContentResponseBean getContent() {
		return content;
	}

	public void setContent(NewsContentResponseBean content) {
		this.content = content;
	}

	public int getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}
}
