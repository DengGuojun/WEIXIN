package com.lpmas.weixin.console.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.console.group.cache.LocalCacheController;
import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.config.WeixinCacheConfig;
import com.lpmas.weixin.config.WeixinConfig;
import com.lpmas.weixin.console.business.WeixinAccountInfoBusiness;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class WeixinAccountInfoCache {
	private static Logger logger = LoggerFactory.getLogger(WeixinAccountInfoCache.class);

	public int getAccountIdByCode(String accountCode) {
		String key = WeixinCacheConfig.getWeixinAccountIdCacheKey(accountCode);
		LocalCache localCache = LocalCache.getInstance();
		Integer accountId = 0;
		Object obj = null;
		try {
			obj = localCache.get(key);
			logger.debug("get accountId from cache");
			accountId = (Integer) obj;
		} catch (NeedsRefreshException e) {
			WeixinAccountInfoBusiness accountInfoBusiness = new WeixinAccountInfoBusiness();
			WeixinAccountInfoBean accountInfoBean = accountInfoBusiness.getWeixinAccountInfoByCode(accountCode);
			if (accountInfoBean != null) {
				accountId = accountInfoBean.getAccountId();
				logger.debug("set accountId to cache");
				localCache.set(key, accountId, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return accountId;
	}

	public String getAccountCodeByKey(int accountId) {
		String key = WeixinCacheConfig.getWeixinAccountCodeCacheKey(accountId);
		LocalCache localCache = LocalCache.getInstance();
		String accountCode = "";
		Object obj = null;
		try {
			obj = localCache.get(key);
			logger.debug("get accountCode from cache");
			accountCode = (String) obj;
		} catch (NeedsRefreshException e) {
			WeixinAccountInfoBusiness accountInfoBusiness = new WeixinAccountInfoBusiness();
			WeixinAccountInfoBean accountInfoBean = accountInfoBusiness.getWeixinAccountInfoByKey(accountId);
			if (accountInfoBean != null) {
				accountCode = accountInfoBean.getAccountCode();
				logger.debug("set accountCode to cache");
				localCache.set(key, accountCode, Constants.CACHE_TIME_2_HOUR);
			}
		}
		return accountCode;
	}

	public void refreshWeixinAccountId(String accountCode) {
		String key = WeixinCacheConfig.getWeixinAccountIdCacheKey(accountCode);
		LocalCache.getInstance().delete(key);
		LocalCacheController.getInstance().refreshLocalCache(WeixinConfig.APP_ID, key);
	}

	public void refreshWeixinAccountCode(int accountId) {
		String key = WeixinCacheConfig.getWeixinAccountCodeCacheKey(accountId);
		LocalCache.getInstance().delete(key);
		LocalCacheController.getInstance().refreshLocalCache(WeixinConfig.APP_ID, key);
	}

	public void refreshWeixinAccountInfoByCondition(String appCode, String storeCode) {
		String key = WeixinCacheConfig.getWeixinAccountInfoCacheKeyByCondition(appCode, storeCode);
		LocalCache.getInstance().delete(key);
		LocalCacheController.getInstance().refreshLocalCache(WeixinConfig.APP_ID, key);
	}

	public void refreshWeixinAccountInfoByCode(String accountCode) {
		String key = WeixinCacheConfig.getWeixinAccountInfoCacheKeyByCode(accountCode);
		LocalCache.getInstance().delete(key);
		LocalCacheController.getInstance().refreshLocalCache(WeixinConfig.APP_ID, key);
	}

}
