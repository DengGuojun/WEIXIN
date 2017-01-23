package com.lpmas.weixin.api.bean.response.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class AddTemplateResponseBean extends WxResponseBaseBean {
	// 模版Id
	@JsonProperty("template_id")
	private String templateId;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
}
