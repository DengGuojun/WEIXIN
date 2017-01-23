package com.lpmas.weixin.api.request.message;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.message.SendMassMessageRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.message.SendMassMessageResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

/**
 * 群发信息
 * 
 * @author Shine Chin
 *
 */
public class SendMassMessage extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(SendMassMessage.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		SendMassMessageResponseBean responseBean = new SendMassMessageResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			SendMassMessageRequestBean bean = (SendMassMessageRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, SendMassMessageResponseBean.class);
		} catch (Exception e) {
			log.error("SendAllByGroup Error:", e);
		}
		return responseBean;
	}
}
