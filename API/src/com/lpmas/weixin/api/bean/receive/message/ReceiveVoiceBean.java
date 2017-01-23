package com.lpmas.weixin.api.bean.receive.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;

public class ReceiveVoiceBean extends MessageBaseBean {

	// 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	@JacksonXmlProperty(localName = "MediaId")
	private String mediaId = "";

	// 语音格式，如amr，speex等。
	@JacksonXmlProperty(localName = "Format")
	private String format = "";

	// 语音识别结果，UTF8编码
	@JacksonXmlProperty(localName = "Recognition")
	private String recognition = "";

	public ReceiveVoiceBean() {
		super(ReceiveMessageTypeConfig.RMT_VOICE);
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
}
