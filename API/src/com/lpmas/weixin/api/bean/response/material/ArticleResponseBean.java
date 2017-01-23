package com.lpmas.weixin.api.bean.response.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.material.ArticleInfoBean;

public class ArticleResponseBean extends ArticleInfoBean {
	private String url;
	@JsonProperty("thumb_url")
	private String thumbUrl;
	@JsonProperty("create_time")
	private int createTime;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
}
