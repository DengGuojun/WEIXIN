package com.lpmas.weixin.api.bean.request.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class AddTemplateRequestBean extends WxRequestBaseBean {

	// 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
	@JsonProperty("template_id_short")
	private String templateShortId = "";

	public String getTemplateShortId() {
		return templateShortId;
	}

	public void setTemplateShortId(String templateShortId) {
		this.templateShortId = templateShortId;
	}
}
