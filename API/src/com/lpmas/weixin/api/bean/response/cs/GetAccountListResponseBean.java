package com.lpmas.weixin.api.bean.response.cs;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;

public class GetAccountListResponseBean extends WxResponseBaseBean {
	@JsonProperty("kf_list")
	private List<AccountBean> accountList = new ArrayList<AccountBean>();

	public List<AccountBean> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<AccountBean> accountList) {
		this.accountList = accountList;
	}
}
