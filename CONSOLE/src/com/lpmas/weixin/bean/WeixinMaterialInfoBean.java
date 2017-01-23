package com.lpmas.weixin.bean;

import java.util.ArrayList;
import java.util.List;

import com.lpmas.framework.nosql.mongodb.MongoDBDocumentBean;
import com.lpmas.weixin.api.bean.response.material.ArticleResponseBean;

public class WeixinMaterialInfoBean extends MongoDBDocumentBean<String> {

	private int accountId = 0;
	private String mediaId = "";
	private String mediaType = "";
	private int updateTime = 0;
	private int createTime;
	private String name = "";
	private String url = "";
	private List<ArticleResponseBean> articleList = new ArrayList<ArticleResponseBean>();

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public int getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public List<ArticleResponseBean> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleResponseBean> articleList) {
		this.articleList = articleList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
