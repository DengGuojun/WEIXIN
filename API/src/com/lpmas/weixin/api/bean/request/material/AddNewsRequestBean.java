package com.lpmas.weixin.api.bean.request.material;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.material.ArticleInfoBean;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class AddNewsRequestBean extends WxRequestBaseBean {
	@JsonProperty("articles")
	private List<ArticleInfoBean> articleList = new ArrayList<ArticleInfoBean>();

	public List<ArticleInfoBean> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleInfoBean> articleList) {
		this.articleList = articleList;
	}
}
