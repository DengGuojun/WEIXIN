package com.lpmas.weixin.api.bean.material;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArticleInfoBean {
	protected String title = "";
	@JsonProperty("thumb_media_id")
	protected String thumbMediaId = "";
	protected String author = "";
	protected String digest = "";
	@JsonProperty("show_cover_pic")
	protected int showCoverPic = 0;
	protected String content = "";
	@JsonProperty("content_source_url")
	protected String contentSourceUrl = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public int getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(int showCoverPic) {
		this.showCoverPic = showCoverPic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentSourceUrl() {
		return contentSourceUrl;
	}

	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}
}
