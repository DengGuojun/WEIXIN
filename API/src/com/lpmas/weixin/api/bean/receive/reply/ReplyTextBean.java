package com.lpmas.weixin.api.bean.receive.reply;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.lpmas.weixin.api.bean.receive.ReplyMessageBaseBean;
import com.lpmas.weixin.api.config.ReplyMessageTypeConfig;

@JacksonXmlRootElement(localName = "xml")
public class ReplyTextBean extends ReplyMessageBaseBean {
	// 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	@JacksonXmlCData
	@JacksonXmlProperty(localName = "Content")
	private String content = "";

	public ReplyTextBean() {
		super(ReplyMessageTypeConfig.PMT_TEXT);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
