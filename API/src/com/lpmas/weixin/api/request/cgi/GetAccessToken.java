package com.lpmas.weixin.api.request.cgi;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.cgi.GetAccessTokenRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.cgi.GetAccessTokenResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetAccessToken extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetAccessToken.class);
	private String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	
	public WxResponseBaseBean execute(GetAccessTokenRequestBean requestBean) {
		GetAccessTokenResponseBean responseBean = new GetAccessTokenResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			String result = apiInvoker.getRequest(
					MessageFormat.format(requestUrl, requestBean.getAppId(), requestBean.getAppSecret()), null);
			parseResponseResult(result, responseBean);
			responseBean = JsonKit.toBean(result, GetAccessTokenResponseBean.class);
		} catch (Exception e) {
			log.error("GetAccessToken Error:", e);
		}
		return responseBean;
	}

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		// TODO Auto-generated method stub
		return null;
	}
}
