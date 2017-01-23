package com.lpmas.weixin.api.bean.response.menu;

import java.util.Map;

import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetMenuResponseBean extends WxResponseBaseBean {
	private Map<String, Object> menuContent;

	public Map<String, Object> getMenuContent() {
		return menuContent;
	}

	public void setMenuContent(Map<String, Object> menuContent) {
		this.menuContent = menuContent;
	}
}
