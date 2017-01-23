package com.lpmas.weixin.api.bean.receive.event;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SendPictureInfoBean {
	@JacksonXmlProperty(localName = "Count")
	private int count;

	@JacksonXmlProperty(localName = "PicList")
	private List<SendPictureItemBean> pictureItemList = new ArrayList<SendPictureItemBean>();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<SendPictureItemBean> getPictureItemList() {
		return pictureItemList;
	}

	public void setPictureItemList(List<SendPictureItemBean> pictureItemList) {
		this.pictureItemList = pictureItemList;
	}
}
