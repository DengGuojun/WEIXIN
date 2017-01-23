package com.lpmas.weixin.console.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.client.WeixinServiceClient;
import com.lpmas.weixin.console.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.console.cache.WeixinConfigCache;

public class WeixinRequestFactory {

	private static Logger log = LoggerFactory.getLogger(WeixinRequestFactory.class);

	@SuppressWarnings("unchecked")
	public static <T> T getRequestBean(String accountCode, Class<T> clazz) {

		if (!WxRequestBaseBean.class.isAssignableFrom(clazz)) {
			throw new IllegalArgumentException(clazz.getName() + "必须是WxRequestBaseBean的子类");
		}
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);

		WeixinConfigCache configCache = new WeixinConfigCache();
		WeixinConfigBean configBean = configCache.getWeixinConfigBean(accountId);
		// CLIENT的TOKEN通过CLIENT方法去
		WeixinServiceClient client = new WeixinServiceClient();
		String token = client.getAccessTokenByAccountCode(accountCode);
		WxRequestBaseBean baseBean = new WxRequestBaseBean();
		try {
			baseBean = (WxRequestBaseBean) clazz.newInstance();
		} catch (Exception e) {
			log.error("", e);
		}
		baseBean.setAccessToken(token);
		baseBean.setAppId(configBean.getAppId());
		baseBean.setSecret(configBean.getAppSecret());
		return (T) baseBean;
	}

}
