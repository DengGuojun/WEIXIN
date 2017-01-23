package com.lpmas.weixin.console.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.console.group.cache.LocalCacheController;
import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.config.WeixinCacheConfig;
import com.lpmas.weixin.config.WeixinConfig;
import com.lpmas.weixin.console.business.WeixinConfigBusiness;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class WeixinConfigCache {

	private static Logger logger = LoggerFactory.getLogger(WeixinConfigCache.class);

	public WeixinConfigBean getWeixinConfigBean(int accountId) {
		String key = WeixinCacheConfig.getWeixinConfigCacheKey(accountId);
		LocalCache localCache = LocalCache.getInstance();
		WeixinConfigBean result = new WeixinConfigBean();
		Object obj = null;
		try {
			obj = localCache.get(key);
			logger.info("get WeixinConfigBean from cache");
			result = JsonKit.toBean((String) obj, WeixinConfigBean.class);
		} catch (NeedsRefreshException e) {
			WeixinConfigBusiness business = new WeixinConfigBusiness();
			result = business.getWeixinConfigBean(accountId);
			if (StringKit.isValid(result.getAppId())&&StringKit.isValid(result.getAppSecret())) {
				logger.info("set WeixinConfigBean to cache");
				localCache.set(key, JsonKit.toJson(result), Constants.CACHE_TIME_2_HOUR);
			}
		}
		return result;
	}

	public void refreshWeixinConfigBean(int accountId) {
		String key = WeixinCacheConfig.getWeixinConfigCacheKey(accountId);
		LocalCache localCache = LocalCache.getInstance();
		localCache.delete(key);
		LocalCacheController.getInstance().refreshLocalCache(WeixinConfig.APP_ID, key);
	}

}
