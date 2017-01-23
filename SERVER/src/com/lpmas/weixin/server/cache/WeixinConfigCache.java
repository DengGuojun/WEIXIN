package com.lpmas.weixin.server.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.config.WeixinCacheConfig;
import com.lpmas.weixin.server.business.WeixinConfigBusiness;
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
			logger.debug("get WeixinConfigBean from cache");
			result = JsonKit.toBean((String) obj, WeixinConfigBean.class);
		} catch (NeedsRefreshException e) {
			WeixinConfigBusiness business = new WeixinConfigBusiness();
			result = business.getWeixinConfigBean(accountId);
			if (result != null) {
				logger.debug("set WeixinConfigBean to cache");
				localCache.set(key, JsonKit.toJson(result), Constants.CACHE_TIME_2_HOUR);
			}
		}
		return result;
	}
}
