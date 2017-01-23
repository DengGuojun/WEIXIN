package com.lpmas.weixin.server.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.weixin.api.bean.request.WxRequestBaseBean;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.server.cache.WeixinAccessTokenCache;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.cache.WeixinConfigCache;

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

		WeixinAccessTokenCache cache = new WeixinAccessTokenCache();
		String token = cache.getAccessToken(accountId);
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
