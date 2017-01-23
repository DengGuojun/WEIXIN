package com.lpmas.weixin.api.bean.request.message.cs;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.message.SendCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class SendVideoCustomServiceMessageRequestBean extends SendCustomServiceMessageRequestBean {
	public SendVideoCustomServiceMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_VIDEO);
	}

	@JsonIgnore
	private String mediaId = "";

	@JsonIgnore
	private String thumbMediaId = "";

	@JsonIgnore
	private String title = "";

	@JsonIgnore
	private String description = "";

	@JsonProperty("video")
	private Map<String, Object> paramMap;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
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

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("media_id", mediaId);
		paramMap.put("thumb_media_id", thumbMediaId);
		paramMap.put("title", title);
		paramMap.put("description", description);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
