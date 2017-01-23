package com.lpmas.weixin.api.bean.request.cs;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UploadHeadImageAccountRequestBean extends WxRequestBaseBean {
	@JsonProperty("kf_account")
	private String account = "";
	// 上传文件 form-data中媒体文件标识，有filename、filelength、content-type等信息
	private File media;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public File getMedia() {
		return media;
	}

	public void setMedia(File media) {
		this.media = media;
	}

}
