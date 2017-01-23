package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class LocationInfoBean {
	// X坐标信息
	@JacksonXmlProperty(localName = "Location_X")
	private String locationX;

	// Y坐标信息
	@JacksonXmlProperty(localName = "Location_Y")
	private String locationY;

	// 精度，可理解为精度或者比例尺、越精细的话 scale越高
	@JacksonXmlProperty(localName = "Scale")
	private String scale;

	// 地理位置的字符串信息
	@JacksonXmlProperty(localName = "Label")
	private String label;

	// 朋友圈POI的名字，可能为空
	@JacksonXmlProperty(localName = "Poiname")
	private String poiName;

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

	public String getPoiName() {
		return poiName;
	}

	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}
}
