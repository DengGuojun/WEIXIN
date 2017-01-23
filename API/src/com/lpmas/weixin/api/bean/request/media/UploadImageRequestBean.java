package com.lpmas.weixin.api.bean.request.media;

import java.io.File;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UploadImageRequestBean extends WxRequestBaseBean {
	private File mediaFile;

	public File getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(File mediaFile) {
		this.mediaFile = mediaFile;
	}
}
