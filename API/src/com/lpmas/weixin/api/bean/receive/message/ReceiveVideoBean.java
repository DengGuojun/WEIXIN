package com.lpmas.weixin.api.bean.receive.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;

public class ReceiveVideoBean extends MessageBaseBean {

	// 视频消息媒体id，可以调用多媒体文件下载接口拉取数据 MediaId
	@JacksonXmlProperty(localName = "MediaId")
	private String mediaId = "";

	// 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。 ThumbMediaId
	@JacksonXmlProperty(localName = "ThumbMediaId")
	private String thumbMediaId = "";

	public ReceiveVideoBean() {
		super(ReceiveMessageTypeConfig.RMT_VIDEO);
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

}
