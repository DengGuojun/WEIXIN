package com.lpmas.weixin.api.bean.response.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

//媒体文件在后台保存时间为3天，即3天后media_id失效。
public class UploadMediaResponseBean extends WxResponseBaseBean {

	// 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
	@JsonProperty("type")
	private String mediaType;

	// 媒体文件上传后，获取时的唯一标识
	@JsonProperty("media_id")
	private String mediaId;

	// 媒体文件上传时间戳
	@JsonProperty("created_at")
	private Long createdAt;

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
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
