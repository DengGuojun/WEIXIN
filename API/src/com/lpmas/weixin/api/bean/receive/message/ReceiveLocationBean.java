package com.lpmas.weixin.api.bean.receive.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.MessageBaseBean;
import com.lpmas.weixin.api.config.ReceiveMessageTypeConfig;

public class ReceiveLocationBean extends MessageBaseBean {

	// 地理位置维度 Location_X
	@JacksonXmlProperty(localName = "Location_X")
	private String locationX = "";

	// 地理位置经度 Location_Y
	@JacksonXmlProperty(localName = "Location_Y")
	private String locationY = "";

	// 地图缩放大小 Scale
	@JacksonXmlProperty(localName = "Scale")
	private String scale = "";

	// 地理位置信息 Label
	@JacksonXmlProperty(localName = "Label")
	private String label = "";

	public ReceiveLocationBean() {
		super(ReceiveMessageTypeConfig.RMT_LOCATION);
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
