package com.lpmas.weixin.api.bean.request.menu;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class CreateMenuRequestBean extends WxRequestBaseBean{

	private String menuContent = "";

	public String getMenuContent() {
		return menuContent;
	}

	public void setMenuContent(String menuContent) {
		this.menuContent = menuContent;
	}
	
}
