package com.lpmas.weixin.api.bean.response.auth;

import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetAuthorizeUrlResponseBean extends WxResponseBaseBean{
	private String url="";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
