package com.lpmas.weixin.api.bean.request.message.user;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.message.SendMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class SendVoiceMessageRequestBean extends SendMessageRequestBean {
	public SendVoiceMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_VOICE);
	}

	@JsonIgnore
	private String mediaId = "";
	@JsonProperty("voice")
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
