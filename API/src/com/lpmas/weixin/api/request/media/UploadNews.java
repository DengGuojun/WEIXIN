package com.lpmas.weixin.api.request.media;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.media.UploadNewsRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.media.UploadNewsResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

/**
 * 上传图文信息，接口文档在“群发消息接口”
 * 
 * @author Shine Chin
 *
 */
public class UploadNews extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(UploadNews.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		UploadNewsResponseBean responseBean = new UploadNewsResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			UploadNewsRequestBean bean = (UploadNewsRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, UploadNewsResponseBean.class);
		} catch (Exception e) {
			log.error("UploadNews Error:", e);
		}
		return responseBean;
	}
}
