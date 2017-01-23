package com.lpmas.weixin.api.request.media;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiFileInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.media.UploadMediaRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.media.UploadMediaResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class UploadMedia extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UploadMedia.class);
	private String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token={0}&type={1}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UploadMediaResponseBean responseBean = new UploadMediaResponseBean();
		ApiFileInvoker apiInvoker = new ApiFileInvoker();
		try {
			UploadMediaRequestBean bean = (UploadMediaRequestBean) requestBean;
			String result = apiInvoker.uploadFile(
					MessageFormat.format(requestUrl, requestBean.getAccessToken(), bean.getMediaType()), "media",
					bean.getMediaFile());
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, UploadMediaResponseBean.class);
		} catch (Exception e) {
			log.error("UploadMedia Error:", e);
		}
		return responseBean;
	}
}
