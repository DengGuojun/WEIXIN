package com.lpmas.weixin.server.cache;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.bean.AutoReplyMessageInfoBean;
import com.lpmas.weixin.config.WeixinCacheConfig;
import com.lpmas.weixin.server.business.AutoReplyMessageInfoBusiness;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class AutoReplyMessageInfoCache {
	private static Logger logger = LoggerFactory.getLogger(AutoReplyMessageInfoCache.class);

	public List<AutoReplyMessageInfoBean> getAutoReplyMessageInfoListByType(int accountId, int replyType) {
		String key = WeixinCacheConfig.getAutoReplyMessageInfoListCacheKey(accountId, replyType);
		LocalCache localCache = LocalCache.getInstance();
		Object obj = null;
		List<AutoReplyMessageInfoBean> result = new ArrayList<AutoReplyMessageInfoBean>();
		try {
			obj = localCache.get(key);
			logger.debug("get List<AutoReplyMessageInfoBean> from cache");
			result = JsonKit.toList((String) obj, AutoReplyMessageInfoBean.class);
		} catch (NeedsRefreshException e) {
			AutoReplyMessageInfoBusiness business = new AutoReplyMessageInfoBusiness();
			result = business.getAutoReplyMessageInfoListByType(accountId, replyType);
			if (result != null) {
				logger.debug("set List<AutoReplyMessageInfoBean> to cache");
				localCache.set(key, JsonKit.toJson(result), Constants.CACHE_TIME_2_HOUR);
			}
		}
		return result;
	}
}
