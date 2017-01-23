package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SendPictureItemBean {
	// 图片的MD5值，开发者若需要，可用于验证接收到图片
	@JacksonXmlProperty(localName = "PicMd5Sum")
	private String pictureMd5Sum;

	public String getPictureMd5Sum() {
		return pictureMd5Sum;
	}

	public void setPictureMd5Sum(String pictureMd5Sum) {
		this.pictureMd5Sum = pictureMd5Sum;
	}
}
