package com.lpmas.weixin.api.bean.request.message.cs;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.message.SendCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class SendMusicCustomServiceMessageRequestBean extends SendCustomServiceMessageRequestBean {
	public SendMusicCustomServiceMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_MUSIC);
	}

	@JsonIgnore
	private String musicUrl = "";

	@JsonIgnore
	private String hqMusicUrl = "";

	@JsonIgnore
	private String thumbMediaId = "";

	@JsonIgnore
	private String title = "";

	@JsonIgnore
	private String description = "";

	@JsonProperty("music")
	private Map<String, Object> paramMap;

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
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
		paramMap.put("musicurl", musicUrl);
		paramMap.put("hqmusicurl", hqMusicUrl);
		paramMap.put("thumb_media_id", thumbMediaId);
		paramMap.put("title", title);
		paramMap.put("description", description);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
