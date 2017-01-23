package com.lpmas.weixin.api.bean.receive.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;

public class ReceiveLinkBean extends MessageBaseBean {

	// 消息标题 Title
	@JacksonXmlProperty(localName = "Title")
	private String title = "";

	// 消息描述 Description
	@JacksonXmlProperty(localName = "Description")
	private String description = "";

	// 消息链接 Url
	@JacksonXmlProperty(localName = "Url")
	private String url = "";

	public ReceiveLinkBean() {
		super(ReceiveMessageTypeConfig.RMT_LINK);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
