package com.lpmas.weixin.api.request.template;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.template.SendTemplateMessageResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class SendTemplateMessage extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(SendTemplateMessage.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		SendTemplateMessageResponseBean responseBean = new SendTemplateMessageResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(requestBean));
			parseResponseResult(result, responseBean);

			responseBean = JsonKit.toBean(result, SendTemplateMessageResponseBean.class);
		} catch (Exception e) {
			log.error("SendTemplateMsg Error:", e);
		}
		return responseBean;
	}
}
