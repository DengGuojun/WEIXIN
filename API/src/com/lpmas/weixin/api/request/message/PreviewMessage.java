package com.lpmas.weixin.api.request.message;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.message.PreviewMessageRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.message.PreviewMessageResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class PreviewMessage extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(PreviewMessage.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		PreviewMessageResponseBean responseBean = new PreviewMessageResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			PreviewMessageRequestBean bean = (PreviewMessageRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, PreviewMessageResponseBean.class);
		} catch (Exception e) {
			log.error("PreviewSendAllMsg Error:", e);
		}
		return responseBean;
	}
}
