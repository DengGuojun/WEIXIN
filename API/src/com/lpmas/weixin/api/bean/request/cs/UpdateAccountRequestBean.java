package com.lpmas.weixin.api.bean.request.cs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UpdateAccountRequestBean extends WxRequestBaseBean {
	@JsonProperty("kf_account")
	private String account = "";
	@JsonProperty("nickname")
	private String nickName = "";
	@JsonProperty("password")
	private String password = "";

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
