package com.lpmas.weixin.api.bean.receive.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;

public class ReceiveImageBean extends MessageBaseBean {
	// 图片链接 PicUrl
	@JacksonXmlProperty(localName = "PicUrl")
	private String picUrl = "";

	// 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。 MediaId
	@JacksonXmlProperty(localName = "MediaId")
	private String mediaId = "";

	public ReceiveImageBean() {
		super(ReceiveMessageTypeConfig.RMT_IMAGE);
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
