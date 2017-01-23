package com.lpmas.weixin.api.bean.request.message.cs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.material.NewsInfoBean;
import com.lpmas.weixin.api.bean.request.message.SendCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.config.SendMessageTypeConfig;

public class SendLinkNewsCustomServiceMessageRequestBean extends SendCustomServiceMessageRequestBean {
	public SendLinkNewsCustomServiceMessageRequestBean() {
		super(SendMessageTypeConfig.SMT_LINK_NEWS);
	}

	@JsonIgnore
	private List<NewsInfoBean> articleList;

	@JsonProperty("news")
	private Map<String, Object> paramMap;

	public List<NewsInfoBean> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<NewsInfoBean> articleList) {
		this.articleList = articleList;
	}

	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("articles", articleList);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
