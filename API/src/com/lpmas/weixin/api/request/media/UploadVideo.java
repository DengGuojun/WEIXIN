package com.lpmas.weixin.api.request.media;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.media.UploadVideoRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.media.UploadVideoResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

/**
 * 上传视频信息，接口文档在“群发消息”接口
 * 
 * @author Shine Chin
 *
 */
public class UploadVideo extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UploadVideo.class);
	private String requestUrl = "https://file.api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UploadVideoResponseBean responseBean = new UploadVideoResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			UploadVideoRequestBean bean = (UploadVideoRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, UploadVideoResponseBean.class);
		} catch (Exception e) {
			log.error("UploadVideo Error:", e);
		}
		return responseBean;
	}
}
