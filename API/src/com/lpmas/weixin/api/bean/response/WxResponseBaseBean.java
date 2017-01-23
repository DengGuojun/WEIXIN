package com.lpmas.weixin.api.bean.response;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WxResponseBaseBean {
	@JsonProperty("errcode") 
	protected int errCode = 0;
	@JsonProperty("errmsg")
	protected String errMsg = "";
	protected Map<String, Object> resultMap = null;

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
}
