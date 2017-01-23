package com.lpmas.weixin.api.request.message;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.message.SendCustomServiceMessageRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.message.SendCustomServiceResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

/**
 * 发送客服信息
 * 
 * @author Shine Chin
 *
 */
public class SendCustomServiceMessage extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(SendCustomServiceMessage.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		SendCustomServiceResponseBean responseBean = new SendCustomServiceResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			SendCustomServiceMessageRequestBean bean = (SendCustomServiceMessageRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, SendCustomServiceResponseBean.class);
		} catch (Exception e) {
			log.error("SendKfMsg Error:", e);
		}
		return responseBean;
	}
}
