package com.lpmas.weixin.api.request.media;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiFileInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.media.UploadImageRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.media.UploadImageResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class UploadImage extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UploadImage.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token={0}";
	
	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UploadImageResponseBean responseBean = new UploadImageResponseBean();
		ApiFileInvoker apiInvoker = new ApiFileInvoker();
		try {
			UploadImageRequestBean bean = (UploadImageRequestBean) requestBean;

			String result = apiInvoker.uploadFile(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					"media", bean.getMediaFile(), null);
			parseResponseResult(result, responseBean);

			System.out.println(result);
			responseBean = JsonKit.toBean(result, UploadImageResponseBean.class);
		} catch (Exception e) {
			log.error("UploadMedia Error:", e);
		}
		return responseBean;
	}
}
