package com.lpmas.weixin.api.bean.request.media;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class GetMediaRequestBean extends WxRequestBaseBean {

	// 媒体文件ID
	private String mediaId = "";

	// 文件存放的文件夹路径
	private String filePath = "";

	// 否 文件保存的文件名称（不包含扩展名，默认从下载文件名中取）为null或空时取返回的文件名称
	private String fileName = "";

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
