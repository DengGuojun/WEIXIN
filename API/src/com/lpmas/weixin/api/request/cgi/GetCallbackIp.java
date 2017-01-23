package com.lpmas.weixin.api.request.cgi;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cgi.GetCallbackIpResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetCallbackIp extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetCallbackIp.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token={0}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetCallbackIpResponseBean responseBean = new GetCallbackIpResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAccessToken()), null);

			parseResponseResult(result, responseBean);
			responseBean = JsonKit.toBean(result, GetCallbackIpResponseBean.class);
		} catch (Exception e) {
			log.error("GetWxIPList Error:", e);
		}
		return responseBean;
	}
}
