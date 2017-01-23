package com.lpmas.weixin.api.bean.request.auth;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class GetAuthorizeAccessTokenRequestBean extends WxRequestBaseBean {

	// 填写第一步获取的code参数
	private String code = "";

	// 填写为authorization_code
	private String grantType = "authorization_code";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
}
