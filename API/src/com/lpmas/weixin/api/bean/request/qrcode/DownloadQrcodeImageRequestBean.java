package com.lpmas.weixin.api.bean.request.qrcode;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;

public class DownloadQrcodeImageRequestBean extends WxRequestBaseBean {
	// 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码。
	private String ticket = "";

	// 文件存放的文件夹路径
	private String filePath = "";

	// 否 文件保存的文件名称（不包含扩展名，默认从下载文件名中取）为null或空时取返回的文件名称
	private String fileName = "";

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
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
