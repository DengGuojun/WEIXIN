package com.lpmas.weixin.api.bean.receive.reply;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.lpmas.weixin.api.bean.receive.ReplyMessageBaseBean;
import com.lpmas.weixin.api.config.ReplyMessageTypeConfig;

@JacksonXmlRootElement(localName = "xml")
public class ReplyVoiceBean extends ReplyMessageBaseBean {

	// 通过上传多媒体文件，得到的id
	@JsonIgnore
	private String mediaId = "";

	@JacksonXmlProperty(localName = "Voice")
	@JacksonXmlCData
	private Map<String, Object> paramMap;

	public ReplyVoiceBean() {
		super(ReplyMessageTypeConfig.PMT_VOICE);
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("MediaId", mediaId);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
