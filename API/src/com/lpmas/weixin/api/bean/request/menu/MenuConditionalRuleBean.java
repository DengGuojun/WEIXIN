package com.lpmas.weixin.api.bean.request.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuConditionalRuleBean {

	@JsonProperty("tag_id")
	private String tagId = "";
	@JsonProperty("sex")
	private String sex = "";
	@JsonProperty("country")
	private String country = "";
	@JsonProperty("province")
	private String province = "";
	@JsonProperty("city")
	private String city = "";
	@JsonProperty("client_platform_type")
	private String clientPlatformType = "";
	@JsonProperty("language")
	private String language = "";

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClientPlatformType() {
		return clientPlatformType;
	}

	public void setClientPlatformType(String clientPlatformType) {
		this.clientPlatformType = clientPlatformType;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
