package com.lpmas.weixin.api.bean.request.menu;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class CreateConditionalMenuRequestBean extends WxRequestBaseBean {
	@JsonProperty("button")
	private List<Map<String, Object>> menuContent;
	@JsonProperty("matchrule")
	private MenuConditionalRuleBean ruleContent;

	public List<Map<String, Object>> getMenuContent() {
		return menuContent;
	}

	public void setMenuContent(List<Map<String, Object>> menuContent) {
		this.menuContent = menuContent;
	}

	public MenuConditionalRuleBean getRuleContent() {
		return ruleContent;
	}

	public void setRuleContent(MenuConditionalRuleBean ruleContent) {
		this.ruleContent = ruleContent;
	}
}
