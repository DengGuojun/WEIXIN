package com.lpmas.weixin.api.bean.request.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.material.ArticleInfoBean;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UpdateMaterialRequestBean extends WxRequestBaseBean {
	@JsonProperty("media_id")
	private String mediaId;
	@JsonProperty("index")
	private int mediaIndex;
	@JsonProperty("articles")
	private ArticleInfoBean articleInfo;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public int getMediaIndex() {
		return mediaIndex;
	}

	public void setMediaIndex(int mediaIndex) {
		this.mediaIndex = mediaIndex;
	}

	public ArticleInfoBean getArticleInfo() {
		return articleInfo;
	}

	public void setArticleInfo(ArticleInfoBean articleInfo) {
		this.articleInfo = articleInfo;
	}
}
