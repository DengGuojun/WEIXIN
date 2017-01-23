package com.lpmas.weixin.api.request.message;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.message.SendMessageRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.message.SendMessageResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

/**
 * 指定用户openId列表发送信息
 * 
 * @author Shine Chin
 *
 */
public class SendMessage extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(SendMessage.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		SendMessageResponseBean responseBean = new SendMessageResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			SendMessageRequestBean bean = (SendMessageRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, SendMessageResponseBean.class);
		} catch (Exception e) {
			log.error("SendAllByTousers Error:", e);
		}
		return responseBean;
	}
}
