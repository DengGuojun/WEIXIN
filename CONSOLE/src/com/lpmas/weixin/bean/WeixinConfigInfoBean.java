package com.lpmas.weixin.bean;

import com.lpmas.framework.annotation.FieldTag;

public class WeixinConfigInfoBean {
	@FieldTag(name = "配置ID")
	private int configId = 0;
	@FieldTag(name = "配置代码")
	private String configCode = "";
	@FieldTag(name = "配置名称")
	private String configName = "";
	@FieldTag(name = "配置值")
	private String configValue = "";
	@FieldTag(name = "备注")
	private String memo = "";

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}