package com.lpmas.weixin.api.bean.response.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetMaterialCountResponseBean extends WxResponseBaseBean {
	@JsonProperty("voice_count")
	private int voiceCount;
	@JsonProperty("video_count")
	private int videoCount;
	@JsonProperty("image_count")
	private int imageCount;
	@JsonProperty("news_count")
	private int newsCount;

	public int getVoiceCount() {
		return voiceCount;
	}

	public void setVoiceCount(int voiceCount) {
		this.voiceCount = voiceCount;
	}

	public int getVideoCount() {
		return videoCount;
	}

	public void setVideoCount(int videoCount) {
		this.videoCount = videoCount;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public int getNewsCount() {
		return newsCount;
	}

	public void setNewsCount(int newsCount) {
		this.newsCount = newsCount;
	}
}
