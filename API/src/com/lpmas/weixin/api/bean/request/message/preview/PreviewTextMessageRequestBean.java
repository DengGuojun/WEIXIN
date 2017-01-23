package com.lpmas.weixin.api.bean.request.message.preview;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.message.PreviewMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class PreviewTextMessageRequestBean extends PreviewMessageRequestBean {
	public PreviewTextMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_TEXT);
	}

	@JsonIgnore
	private String content = "";
	@JsonProperty("text")
	private Map<String, Object> paramMap;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("content", content);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
