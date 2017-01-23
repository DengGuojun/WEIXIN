package com.lpmas.weixin.api.request.message;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.message.GetMassMessageStatusRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.message.GetMassMessageStatusResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetMassMessageStatus extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetMassMessageStatus.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetMassMessageStatusResponseBean responseBean = new GetMassMessageStatusResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			GetMassMessageStatusRequestBean bean = (GetMassMessageStatusRequestBean) requestBean;
			String result = apiInvoker.postRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()),
					JsonKit.toJson(bean));

			responseBean = JsonKit.toBean(result, GetMassMessageStatusResponseBean.class);
		} catch (Exception e) {
			log.error("QuerySendAllMsgStatus Error:", e);
		}
		return responseBean;
	}
}
