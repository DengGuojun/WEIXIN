package com.lpmas.weixin.api.request.auth;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.auth.RefreshAuthorizeAccessTokenRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.auth.RefreshAuthorizeAccessTokenResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class RefreshAuthorizeAccessToken extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(RefreshAuthorizeAccessToken.class);
	private String requestUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid={0}&grant_type={1}&refresh_token={2}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		RefreshAuthorizeAccessTokenResponseBean responseBean = new RefreshAuthorizeAccessTokenResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			RefreshAuthorizeAccessTokenRequestBean bean = (RefreshAuthorizeAccessTokenRequestBean) requestBean;
			String result = apiInvoker.getRequest(MessageFormat.format(requestUrl, requestBean.getAppId(),
					bean.getGrantType(), bean.getRefreshToken()), null);
			
			responseBean = JsonKit.toBean(result, RefreshAuthorizeAccessTokenResponseBean.class);
		} catch (Exception e) {
			log.error("RefreshAuthorizeAccessToken Error:", e);
		}
		return responseBean;
	}
}
