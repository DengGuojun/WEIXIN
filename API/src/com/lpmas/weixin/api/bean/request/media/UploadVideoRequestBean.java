package com.lpmas.weixin.api.bean.request.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UploadVideoRequestBean extends WxRequestBaseBean {

	// 此处media_id需通过基础支持中的上传下载多媒体文件来得到
	@JsonProperty("media_id")
	private String mediaId = "";
	private String title = "";
	private String description = "";

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}