package com.lpmas.weixin.api.bean.response.menu;

import java.util.Map;

import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetCurrentSelfMenuInfoResponseBean extends WxResponseBaseBean {
	private Map<String, Object> resultMap;

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
}
