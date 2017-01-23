package com.lpmas.weixin.console.cache;

import com.lpmas.console.group.cache.LocalCacheController;
import com.lpmas.framework.cache.LocalCache;
import com.lpmas.weixin.config.WeixinCacheConfig;
import com.lpmas.weixin.config.WeixinConfig;

public class AutoReplyMessageInfoCache {

	public void refreshAutoReplyMessageInfoListByType(int accountId, int replyType) {
		String key = WeixinCacheConfig.getAutoReplyMessageInfoListCacheKey(accountId, replyType);
		LocalCache.getInstance().delete(key);
		LocalCacheController.getInstance().refreshLocalCache(WeixinConfig.APP_ID, key);
	}

}
