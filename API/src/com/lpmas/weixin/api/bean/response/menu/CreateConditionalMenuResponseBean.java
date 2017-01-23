package com.lpmas.weixin.api.bean.response.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class CreateConditionalMenuResponseBean extends WxResponseBaseBean {
	@JsonProperty("menuid")
	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
