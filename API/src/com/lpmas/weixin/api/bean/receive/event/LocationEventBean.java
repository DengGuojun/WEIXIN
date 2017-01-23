package com.lpmas.weixin.api.bean.receive.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.lpmas.weixin.api.bean.receive.EventBaseBean;
import com.lpmas.weixin.api.config.ReceiveEventConfig;

public class LocationEventBean extends EventBaseBean {
	// 地理位置纬度
	@JacksonXmlProperty(localName = "Latitude")
	private String latitude = "";

	// 地理位置经度
	@JacksonXmlProperty(localName = "Longitude")
	private String longitude = "";

	// 地理位置精度
	@JacksonXmlProperty(localName = "Precision")
	private String precision = "";

	public LocationEventBean() {
		super(ReceiveEventConfig.EVENT_LOCATION);
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

}
