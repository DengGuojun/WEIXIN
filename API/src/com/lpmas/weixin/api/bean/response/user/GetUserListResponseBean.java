package com.lpmas.weixin.api.bean.response.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetUserListResponseBean extends WxResponseBaseBean {

	// 关注该公众账号的总用户数
	private int total;

	// 关注该公众账号的总用户数
	private int count;

	// 关注该公众账号的总用户数
	@JsonProperty("next_openid")
	private String nextOpenId;

	// 拉取列表的后一个用户的OPENID
	private Map<String,Object> data;

	private List<String> openIdList = new ArrayList<String>();

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

}
