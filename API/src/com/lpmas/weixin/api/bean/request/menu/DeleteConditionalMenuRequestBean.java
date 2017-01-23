package com.lpmas.weixin.api.bean.request.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class DeleteConditionalMenuRequestBean extends WxRequestBaseBean {
	@JsonProperty("menuid")
	private String menuId = "";

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
