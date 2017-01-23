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
public class ReplyMusicBean extends ReplyMessageBaseBean {
	// 音乐标题
	@JsonIgnore
	private String title = "";

	// 音乐描述
	@JsonIgnore
	private String description = "";

	// 音乐链接
	@JsonIgnore
	private String musicUrl = "";

	// 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	@JsonIgnore
	private String hqMusicUrl = "";

	// 缩略图的媒体id，通过上传多媒体文件，得到的id
	@JsonIgnore
	private String thumbMediaId = "";

	@JacksonXmlProperty(localName = "Music")
	@JacksonXmlCData
	private Map<String, Object> paramMap;

	public ReplyMusicBean() {
		super(ReplyMessageTypeConfig.PMT_MUSIC);
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

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("Title", title);
		paramMap.put("Description", description);
		paramMap.put("MusicUrl", musicUrl);
		paramMap.put("HQMusicUrl", hqMusicUrl);
		paramMap.put("ThumbMediaId", thumbMediaId);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}