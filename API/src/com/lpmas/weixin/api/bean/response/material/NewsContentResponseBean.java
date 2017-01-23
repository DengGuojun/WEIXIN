package com.lpmas.weixin.api.bean.response.material;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class NewsContentResponseBean extends WxResponseBaseBean {
	@JsonProperty("create_time")
	private int createTime;
	@JsonProperty("update_time")
	private int updateTime;
	@JsonProperty("news_item")
	private List<ArticleResponseBean> articleList = new ArrayList<ArticleResponseBean>();

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}

	public int getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(int updateTime) {
		this.updateTime = updateTime;
	}

	public List<ArticleResponseBean> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleResponseBean> articleList) {
		this.articleList = articleList;
	}
}
