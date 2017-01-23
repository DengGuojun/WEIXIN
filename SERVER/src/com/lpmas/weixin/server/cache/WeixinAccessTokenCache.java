package com.lpmas.weixin.server.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.RemoteCache;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.config.WeixinCacheConfig;
import com.lpmas.weixin.config.WeixinConfig;
import com.lpmas.weixin.server.business.WeixinAccessTokenBusiness;

public class WeixinAccessTokenCache {
	private static Logger logger = LoggerFactory.getLogger(WeixinAccessTokenCache.class);

	public String getAccessToken(int accountId) {
		WeixinConfigCache cache = new WeixinConfigCache();
		WeixinConfigBean configBean = cache.getWeixinConfigBean(accountId);
		String key = WeixinCacheConfig.getWeixinAccessTokenCacheKey(configBean.getAppId());
		RemoteCache remoteCache = RemoteCache.getInstance();
		Object obj = remoteCache.get(WeixinConfig.APP_ID, key);
		String result = "";
		if (obj != null) {
			logger.debug("get accessToken from cache");
			result = (String) obj;
		} else {
			WeixinAccessTokenBusiness business = new WeixinAccessTokenBusiness();
			result = business.getAccessToken(configBean.getAppId(), configBean.getAppSecret());
			if (StringKit.isValid(result)) {
				logger.debug("set accessToken to cache");
				remoteCache.set(WeixinConfig.APP_ID, key, result, WeixinCacheConfig.WEIXIN_ACCESS_TOKEN_TIME);
			}
		}
		return result;
	}

	public boolean refreshAccessToken(int accountId) {
		WeixinConfigCache cache = new WeixinConfigCache();
		WeixinConfigBean configBean = cache.getWeixinConfigBean(accountId);
		String key = WeixinCacheConfig.getWeixinAccessTokenCacheKey(configBean.getAppId());
		RemoteCache remoteCache = RemoteCache.getInstance();
		logger.info("refresh accessToken to cache");
		return remoteCache.delete(WeixinConfig.APP_ID, key);
	}

	public boolean setAccessToken(int accountId) {
		WeixinConfigCache cache = new WeixinConfigCache();
		WeixinConfigBean configBean = cache.getWeixinConfigBean(accountId);
		String key = WeixinCacheConfig.getWeixinAccessTokenCacheKey(configBean.getAppId());
		RemoteCache remoteCache = RemoteCache.getInstance();
		remoteCache.delete(WeixinConfig.APP_ID, key);

		WeixinAccessTokenBusiness business = new WeixinAccessTokenBusiness();
		String token = business.getAccessToken(configBean.getAppId(), configBean.getAppSecret());

		logger.info("set accessToken to cache");
		return remoteCache.set(WeixinConfig.APP_ID, key, token, WeixinCacheConfig.WEIXIN_ACCESS_TOKEN_TIME);
	}

}
