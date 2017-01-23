package com.lpmas.weixin.api.bean.response.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class UploadNewsResponseBean extends WxResponseBaseBean {

	// 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），次数为news，即图文消息
	private String type;

	// 媒体文件/图文消息上传后获取的唯一标识
	@JsonProperty("media_id")
	private String mediaId;

	// 媒体文件上传时间
	@JsonProperty("created_at")
	private Long createdAt;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
}
