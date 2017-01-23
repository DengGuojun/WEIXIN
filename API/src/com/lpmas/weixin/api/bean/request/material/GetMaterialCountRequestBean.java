package com.lpmas.weixin.api.bean.request.material;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class GetMaterialCountRequestBean extends WxRequestBaseBean {
	@JsonProperty("title")
	private String fileTitle;
	@JsonProperty("introduction")
	private String fileIntroduction;
	@JsonIgnore
	private File mediaFile;

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileIntroduction() {
		return fileIntroduction;
	}

	public void setFileIntroduction(String fileIntroduction) {
		this.fileIntroduction = fileIntroduction;
	}

	public File getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(File mediaFile) {
		this.mediaFile = mediaFile;
	}
}
