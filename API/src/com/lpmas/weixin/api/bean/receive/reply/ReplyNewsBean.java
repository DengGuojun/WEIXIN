package com.lpmas.weixin.api.bean.receive.reply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.lpmas.weixin.api.bean.material.NewsInfoBean;
import com.lpmas.weixin.api.bean.receive.ReplyMessageBaseBean;
import com.lpmas.weixin.api.config.ReplyMessageTypeConfig;

@JacksonXmlRootElement(localName = "xml")
public class ReplyNewsBean extends ReplyMessageBaseBean {
	// 是 图文消息个数，限制为10条以内
	@JacksonXmlProperty(localName = "ArticleCount")
	private int articleCount;

	// 是 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	//@JacksonXmlProperty(localName = "Articles")
	@JsonIgnore
	private List<NewsInfoBean> articleList = new ArrayList<NewsInfoBean>();
	
	@JacksonXmlProperty(localName = "Articles")
	@JacksonXmlCData
	private Map<String, Object> paramMap;

	public ReplyNewsBean() {
		super(ReplyMessageTypeConfig.PMT_NEWS);
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public List<NewsInfoBean> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<NewsInfoBean> articleList) {
		this.articleList = articleList;
	}
	
	public Map<String, Object> getParamMap() {
		paramMap = new HashMap<String, Object>();
		paramMap.put("item", articleList);
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
