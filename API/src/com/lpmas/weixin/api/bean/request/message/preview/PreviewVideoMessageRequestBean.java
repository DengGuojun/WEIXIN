package com.lpmas.weixin.api.bean.request.message.preview;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.message.PreviewMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class PreviewVideoMessageRequestBean extends PreviewMessageRequestBean {
	public PreviewVideoMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_VIDEO);
	}

	@JsonIgnore
	private String mediaId = "";
	@JsonProperty("mpvideo")
	private Map<String, Object> paramMap;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("media_id", mediaId);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
