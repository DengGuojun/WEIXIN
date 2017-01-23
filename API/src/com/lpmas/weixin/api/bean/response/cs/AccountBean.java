package com.lpmas.weixin.api.bean.response.cs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountBean {
	@JsonProperty("kf_account")
	private String account;
	@JsonProperty("kf_nick")
	private String nickName;
	@JsonProperty("kf_id")
	private String accountId;
	@JsonProperty("kf_headimg")
	private String headImageUrl;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getHeadImageUrl() {
		return headImageUrl;
	}

	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}

}
