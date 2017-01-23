package com.lpmas.weixin.api.request.auth;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.auth.CheckAuthorizeAccessTokenValidRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.auth.CheckAuthorizeAccessTokenValidResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class CheckAuthorizeAccessTokenValid extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(CheckAuthorizeAccessTokenValid.class);
	private String requestUrl = "https://api.weixin.qq.com/sns/auth?access_token={0}&openid={1}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		CheckAuthorizeAccessTokenValidResponseBean responseBean = new CheckAuthorizeAccessTokenValidResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			CheckAuthorizeAccessTokenValidRequestBean bean = (CheckAuthorizeAccessTokenValidRequestBean) requestBean;
			String result = apiInvoker
					.getRequest(MessageFormat.format(requestUrl, bean.getAccessToken(), bean.getOpenId()), null);

			responseBean = JsonKit.toBean(result, CheckAuthorizeAccessTokenValidResponseBean.class);
		} catch (Exception e) {
			log.error("CheckAuthorizeAccessTokenValid Error:", e);
		}
		return responseBean;
	}
}
