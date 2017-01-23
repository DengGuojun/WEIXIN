package com.lpmas.weixin.api.request.media;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiFileInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.media.GetMediaRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.media.GetMediaResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetMedia extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetMedia.class);
	private String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token={0}&media_id={1}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetMediaResponseBean responseBean = new GetMediaResponseBean();
		ApiFileInvoker apiInvoker = new ApiFileInvoker();
		try {
			GetMediaRequestBean bean = (GetMediaRequestBean) requestBean;
			String result = apiInvoker.downloadFile(
					MessageFormat.format(requestUrl, requestBean.getAccessToken(), bean.getMediaId()),
					bean.getFilePath(), bean.getFileName());
			
			responseBean = JsonKit.toBean(result, GetMediaResponseBean.class);
		} catch (Exception e) {
			log.error("UploadMedia Error:", e);
		}
		return responseBean;
	}
}
