package com.lpmas.weixin.api.request.auth;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.auth.GetAuthorizeAccessTokenRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.auth.GetAuthorizeAccessTokenResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetAuthorizeAccessToken extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetAuthorizeAccessToken.class);
	private String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetAuthorizeAccessTokenResponseBean responseBean = new GetAuthorizeAccessTokenResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			GetAuthorizeAccessTokenRequestBean bean = (GetAuthorizeAccessTokenRequestBean) requestBean;
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, bean.getAppId(),
					bean.getSecret(), bean.getCode()), null);
			
			responseBean = JsonKit.toBean(result, GetAuthorizeAccessTokenResponseBean.class);
		} catch (Exception e) {
			log.error("GetAuthorizeAccessToken Error:", e);
		}
		return responseBean;
	}
}
