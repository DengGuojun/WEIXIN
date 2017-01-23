package com.lpmas.weixin.api.request.qrcode;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiFileInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.qrcode.DownloadQrcodeImageRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.qrcode.DownloadQrcodeImageResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class DownloadQrcodeImage extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(DownloadQrcodeImage.class);
	private String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		DownloadQrcodeImageResponseBean responseBean = new DownloadQrcodeImageResponseBean();
		ApiFileInvoker apiInvoker = new ApiFileInvoker();
		try {
			DownloadQrcodeImageRequestBean bean = (DownloadQrcodeImageRequestBean) requestBean;
			String result = apiInvoker.downloadFile(MessageFormat.format(requestUrl, bean.getTicket()),
					bean.getFilePath(), bean.getFileName());
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, DownloadQrcodeImageResponseBean.class);
		} catch (Exception e) {
			log.error("DownloadQrcodeImage Error:", e);
		}
		return responseBean;
	}
}
