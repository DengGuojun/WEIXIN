package com.lpmas.weixin.api.bean.response.cgi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class ShortUrlResponseBean extends WxResponseBaseBean{
	
	//短链接
	@JsonProperty("short_url")
	private String shortUrl;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

}
