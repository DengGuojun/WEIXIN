package com.lpmas.weixin.api.request.auth;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.auth.GetAuthorizeUrlRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.auth.GetAuthorizeUrlResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetAuthorizeUrl extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetAuthorizeUrl.class);
	private String requestUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type={2}&scope={3}&state={4}&connect_redirect=1#wechat_redirect";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetAuthorizeUrlResponseBean responseBean = new GetAuthorizeUrlResponseBean();
		try {
			GetAuthorizeUrlRequestBean bean = (GetAuthorizeUrlRequestBean) requestBean;
			String url = MessageFormat.format(requestUrl, bean.getAppId(), bean.getRedirectUri(),
					bean.getResponseType(), bean.getScope(), bean.getState());
			responseBean.setUrl(url);
		} catch (Exception e) {
			log.error("GetAuthorizeAccessToken Error:", e);
		}
		return responseBean;
	}
}
