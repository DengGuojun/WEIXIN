package com.lpmas.weixin.server.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.cache.LocalCache;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.config.WeixinCacheConfig;
import com.lpmas.weixin.server.business.WeixinAccountInfoBusiness;
import com.opensymphony.oscache.base.NeedsRefreshException;

public class WeixinAccountInfoCache {
	private static Logger logger = LoggerFactory.getLogger(WeixinAccountInfoCache.class);

	public WeixinAccountInfoBean getWeixinAccountInfoByCondition(String appCode, String storeCode) {
		String key = WeixinCacheConfig.getWeixinAccountInfoCacheKeyByCondition(appCode, storeCode);
		LocalCache localCache = LocalCache.getInstance();
		Object obj = null;
		WeixinAccountInfoBean result = new WeixinAccountInfoBean();
		try {
			obj = localCache.get(key);
			logger.debug("get WeixinAccountInfoBean from cache");
			result = JsonKit.toBean((String) obj, WeixinAccountInfoBean.class);
		} catch (NeedsRefreshException e) {
			WeixinAccountInfoBusiness busniess = new WeixinAccountInfoBusiness();
			result = busniess.getWeixinAccountInfoByCondition(appCode, storeCode);
			if (result != null) {
				logger.debug("set WeixinAccountInfoBean to cache");
				localCache.set(key, JsonKit.toJson(result), Constants.CACHE_TIME_2_HOUR);
			}
		}
		return result;
	}

	public WeixinAccountInfoBean getWeixinAccountInfoByCode(String accountCode) {
		String key = WeixinCacheConfig.getWeixinAccountInfoCacheKeyByCode(accountCode);
		LocalCache localCache = LocalCache.getInstance();
		Object obj = null;
		WeixinAccountInfoBean result = new WeixinAccountInfoBean();
		try {
			obj = localCache.get(key);
			logger.info("get WeixinAccountInfoBean from cache");
			result = JsonKit.toBean((String) obj, WeixinAccountInfoBean.class);
		} catch (NeedsRefreshException e) {
			WeixinAccountInfoBusiness busniess = new WeixinAccountInfoBusiness();
			result = busniess.getWeixinAccountInfoByCode(accountCode);
			if (result != null) {
				logger.info("set WeixinAccountInfoBean to cache");
				localCache.set(key, JsonKit.toJson(result), Constants.CACHE_TIME_2_HOUR);
			}
		}
		return result;
	}

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
}
