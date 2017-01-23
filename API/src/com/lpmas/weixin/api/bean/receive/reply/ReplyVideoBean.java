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
public class ReplyVideoBean extends ReplyMessageBaseBean {

	// 通过上传多媒体文件，得到的id
	@JsonIgnore
	private String mediaId = "";

	// 视频消息的标题
	@JsonIgnore
	private String title = "";

	// 视频消息的描述
	@JsonIgnore
	private String description = "";

	@JacksonXmlProperty(localName = "Video")
	@JacksonXmlCData
	private Map<String, Object> paramMap;

	public ReplyVideoBean() {
		super(ReplyMessageTypeConfig.PMT_VIDEO);
	}

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

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("MediaId", mediaId);
		paramMap.put("Title", title);
		paramMap.put("Description", description);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
