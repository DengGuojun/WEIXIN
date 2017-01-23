package com.lpmas.weixin.api.request.auth;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.api.base.ApiInvoker;
import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.api.bean.request.auth.GetAuthorizeUserInfoRequestBean;
import com.lpmas.weixin.api.bean.response.WxResponseBaseBean;
import com.lpmas.weixin.api.bean.response.auth.GetAuthorizeUserInfoResponseBean;
import com.lpmas.weixin.api.request.WxRequestBase;

public class GetAuthorizeUserInfo extends WxRequestBase {
	private static Logger log = LoggerFactory.getLogger(GetAuthorizeUserInfo.class);
	private String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang={2}";

	@Override
	public WxResponseBaseBean execute(WxRequestBaseBean requestBean) {
		GetAuthorizeUserInfoResponseBean responseBean = new GetAuthorizeUserInfoResponseBean();
		ApiInvoker apiInvoker = new ApiInvoker();
		try {
			GetAuthorizeUserInfoRequestBean bean = (GetAuthorizeUserInfoRequestBean) requestBean;
			String result = apiInvoker.getRequest(
					MessageFormat.format(requestUrl, bean.getAccessToken(), bean.getOpenId(), bean.getLang()), null);

			responseBean = JsonKit.toBean(result, GetAuthorizeUserInfoResponseBean.class);
		} catch (Exception e) {
			log.error("GetAuthorizeUserInfo Error:", e);
		}
		return responseBean;
	}
}
