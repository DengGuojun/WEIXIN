package com.lpmas.weixin.api.bean.request.template;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class SendTemplateMessageRequestBean extends WxRequestBaseBean {
	@JsonProperty("touser")
	private String toUser;
	@JsonProperty("template_id")
	private String templateId;
	@JsonProperty("url")
	private String url;
	@JsonProperty("topcolor")
	private String topColor;

	private Map<String, TemplateStatement> data = new LinkedHashMap<String, TemplateStatement>();

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopColor() {
		return topColor;
	}

	public void setTopColor(String topColor) {
		this.topColor = topColor;
	}

	public Map<String, TemplateStatement> getData() {
		return data;
	}

	public void setData(Map<String, TemplateStatement> data) {
		this.data = data;
	}
}
