package com.lpmas.weixin.server.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.weixin.api.bean.request.cgi.GetAccessTokenRequestBean;
import com.lpmas.weixin.api.bean.response.cgi.GetAccessTokenResponseBean;
import com.lpmas.weixin.api.request.cgi.GetAccessToken;
import com.lpmas.weixin.business.WeixinUtil;

public class WeixinAccessTokenBusiness {

	private static Logger log = LoggerFactory.getLogger(WeixinAccessTokenBusiness.class);

	/**
	 * 获取ACCESSTOKEN的方法
	 * 
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	public String getAccessToken(String appId, String appSecret) {
		String token = null;
		GetAccessTokenRequestBean getAccessTokenRequestBean = new GetAccessTokenRequestBean();
		getAccessTokenRequestBean.setAppId(appId);
		getAccessTokenRequestBean.setAppSecret(appSecret);
		GetAccessToken accessToken = new GetAccessToken();
		GetAccessTokenResponseBean responseBean = (GetAccessTokenResponseBean) accessToken
				.execute(getAccessTokenRequestBean);
		if (WeixinUtil.isSuccess(responseBean)) {
			token = responseBean.getAccessToken();
		} else {
			log.error("accesstoken获取失败,errMsg:" + responseBean.getErrMsg());
		}
		return token;
	}

}
