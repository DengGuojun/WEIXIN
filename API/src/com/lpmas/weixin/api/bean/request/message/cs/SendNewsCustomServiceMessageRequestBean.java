package com.lpmas.weixin.api.bean.request.message.cs;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.message.SendCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class SendNewsCustomServiceMessageRequestBean extends SendCustomServiceMessageRequestBean {
	public SendNewsCustomServiceMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_NEWS);
	}

	@JsonIgnore
	private String mediaId = "";
	@JsonProperty("mpnews")
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
