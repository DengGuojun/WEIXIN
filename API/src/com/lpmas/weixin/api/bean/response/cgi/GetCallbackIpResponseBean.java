package com.lpmas.weixin.api.bean.response.cgi;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetCallbackIpResponseBean extends WxResponseBaseBean {
	@JsonProperty("ip_list")
	private List<String> ipList = new ArrayList<String>();

	public List<String> getIpList() {
		return ipList;
	}

	public void setIpList(List<String> ipList) {
		this.ipList = ipList;
	}
}
