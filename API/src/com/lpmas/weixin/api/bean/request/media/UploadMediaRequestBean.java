package com.lpmas.weixin.api.bean.request.media;

import java.io.File;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class UploadMediaRequestBean extends WxRequestBaseBean {

	// 上传文件 form-data中媒体文件标识，有filename、filelength、content-type等信息
	private File mediaFile;

	// 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	private String mediaType;

	public File getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(File mediaFile) {
		this.mediaFile = mediaFile;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
}
