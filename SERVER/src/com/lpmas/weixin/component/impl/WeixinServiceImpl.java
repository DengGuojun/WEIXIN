package com.lpmas.weixin.component.impl;

import java.util.HashMap;

import com.lpmas.framework.util.JsonKit;
import com.lpmas.framework.util.StringKit;
import com.lpmas.weixin.bean.QrcodeInfoBean;
import com.lpmas.weixin.bean.WeixinAccountInfoBean;
import com.lpmas.weixin.bean.WeixinConfigBean;
import com.lpmas.weixin.client.config.WeixinClientConfig;
import com.lpmas.weixin.client.config.WeixinQrcodeConfig;
import com.lpmas.weixin.component._WeixinServiceDisp;
import com.lpmas.weixin.server.business.WeixinQrcodeInfoBusiness;
import com.lpmas.weixin.server.cache.WeixinAccessTokenCache;
import com.lpmas.weixin.server.cache.WeixinAccountInfoCache;
import com.lpmas.weixin.server.cache.WeixinConfigCache;

import Ice.Current;

public class WeixinServiceImpl extends _WeixinServiceDisp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6406818751468672690L;

	@Override
	public String rpc(String method, String params, Current __current) {
		if (method.equals(WeixinClientConfig.GET_TEMPORARY_QRCODE_TICKET)) {
			return getTemporaryQrcodeTicket(params);
		} else if (method.equals(WeixinClientConfig.GET_TEMPORARY_QRCODE_TICKET_BY_ACCOUNT_CODE)) {
			return getTemporaryQrcodeTicketByAccountCode(params);
		} else if (method.equals(WeixinClientConfig.GET_PERMANENT_QRCODE_TICKET)) {
			return getPermanentTicketByAppSceneId(params);
		} else if (method.equals(WeixinClientConfig.GET_PERMANENT_QRCODE_TICKET_BY_ACCOUNT_CODE)) {
			return getPermanentTicketByAppSceneIdByAccountCode(params);
		} else if (method.equals(WeixinClientConfig.GET_ACCESS_TOKEN)) {
			return getAccessToken(params);
		} else if (method.equals(WeixinClientConfig.GET_ACCESS_TOKEN_BY_ACCOUNT_CODE)) {
			return getAccessTokenByAccountCode(params);
		}
		return "";
	}

	private String getTemporaryQrcodeTicket(String params) {
		HashMap<String, String> condMap = JsonKit.toBean(params, HashMap.class);
		String appCode = condMap.get("appCode");
		String storeCode = condMap.get("storeCode");
		String appSceneId = condMap.get("appSceneId");
		String callBackQueue = condMap.get("callBackQueue");
		int expireSeconds = 0;
		try {
			expireSeconds = Integer.valueOf(condMap.get("expireSeconds"));
			if(expireSeconds<0){
				expireSeconds = WeixinQrcodeConfig.TICKET_EXPIRE;
			}
		} catch (Exception e) {
			expireSeconds = WeixinQrcodeConfig.TICKET_EXPIRE;
		}
		// 账号信息准备
		WeixinAccountInfoCache cache = new WeixinAccountInfoCache();
		WeixinAccountInfoBean accountInfoBean = cache.getWeixinAccountInfoByCondition(appCode, storeCode);
		String accountCode = accountInfoBean.getAccountCode();
		int accountId = accountInfoBean.getAccountId();
		// 检查有无传CALLBACKQUQUE
		if (!StringKit.isValid(callBackQueue)) {
			// 查出DEFAULT的QUEUE名替代进去
			WeixinConfigCache configCache = new WeixinConfigCache();
			WeixinConfigBean configBean = configCache.getWeixinConfigBean(accountId);
			callBackQueue = configBean.getQueueName();
		}

		WeixinQrcodeInfoBusiness qrcodeBusniess = new WeixinQrcodeInfoBusiness();
		QrcodeInfoBean bean = qrcodeBusniess.getTemporaryQrcodeInfoByAppSceneId(accountCode, appSceneId, callBackQueue,
				expireSeconds);
		if (bean == null) {
			bean = new QrcodeInfoBean();
		}
		return bean.getTicket();
	}

	private String getTemporaryQrcodeTicketByAccountCode(String params) {
		HashMap<String, String> condMap = JsonKit.toBean(params, HashMap.class);
		String accountCode = condMap.get("accountCode");
		String appSceneId = condMap.get("appSceneId");
		String callBackQueue = condMap.get("callBackQueue");
		int expireSeconds = 0;
		try {
			expireSeconds = Integer.valueOf(condMap.get("expireSeconds"));
			if(expireSeconds<0){
				expireSeconds = WeixinQrcodeConfig.TICKET_EXPIRE;
			}
		} catch (Exception e) {
			expireSeconds = WeixinQrcodeConfig.TICKET_EXPIRE;
		}
		// 账号信息准备
		WeixinAccountInfoCache cache = new WeixinAccountInfoCache();
		int accountId = cache.getAccountIdByCode(accountCode);
		// 检查有无传CALLBACKQUQUE
		if (!StringKit.isValid(callBackQueue)) {
			// 查出DEFAULT的QUEUE名替代进去
			WeixinConfigCache configCache = new WeixinConfigCache();
			WeixinConfigBean configBean = configCache.getWeixinConfigBean(accountId);
			callBackQueue = configBean.getQueueName();
		}

		WeixinQrcodeInfoBusiness qrcodeBusniess = new WeixinQrcodeInfoBusiness();
		QrcodeInfoBean bean = qrcodeBusniess.getTemporaryQrcodeInfoByAppSceneId(accountCode, appSceneId, callBackQueue,
				expireSeconds);
		if (bean == null) {
			bean = new QrcodeInfoBean();
		}
		return bean.getTicket();
	}

	private String getPermanentTicketByAppSceneId(String params) {
		HashMap<String, String> condMap = JsonKit.toBean(params, HashMap.class);
		String appCode = condMap.get("appCode");
		String storeCode = condMap.get("storeCode");
		String appSceneId = condMap.get("appSceneId");
		String callBackQueue = condMap.get("callBackQueue");
		// 账号信息准备
		WeixinAccountInfoCache cache = new WeixinAccountInfoCache();
		WeixinAccountInfoBean accountInfoBean = cache.getWeixinAccountInfoByCondition(appCode, storeCode);
		String accountCode = accountInfoBean.getAccountCode();
		int accountId = accountInfoBean.getAccountId();
		// 检查有无传CALLBACKQUQUE
		if (!StringKit.isValid(callBackQueue)) {
			// 查出DEFAULT的QUEUE名替代进去
			WeixinConfigCache configCache = new WeixinConfigCache();
			WeixinConfigBean configBean = configCache.getWeixinConfigBean(accountId);
			callBackQueue = configBean.getQueueName();
		}

		WeixinQrcodeInfoBusiness qrcodeBusniess = new WeixinQrcodeInfoBusiness();
		QrcodeInfoBean bean = qrcodeBusniess.getPermanentQrcodeInfoByAppSceneId(accountCode, appSceneId, callBackQueue);
		if (bean == null) {
			bean = new QrcodeInfoBean();
		}
		return bean.getTicket();
	}

	private String getPermanentTicketByAppSceneIdByAccountCode(String params) {
		HashMap<String, String> condMap = JsonKit.toBean(params, HashMap.class);
		String accountCode = condMap.get("accountCode");
		String appSceneId = condMap.get("appSceneId");
		String callBackQueue = condMap.get("callBackQueue");
		// 账号信息准备
		WeixinAccountInfoCache cache = new WeixinAccountInfoCache();
		int accountId = cache.getAccountIdByCode(accountCode);
		// 检查有无传CALLBACKQUQUE
		if (!StringKit.isValid(callBackQueue)) {
			// 查出DEFAULT的QUEUE名替代进去
			WeixinConfigCache configCache = new WeixinConfigCache();
			WeixinConfigBean configBean = configCache.getWeixinConfigBean(accountId);
			callBackQueue = configBean.getQueueName();
		}
		WeixinQrcodeInfoBusiness qrcodeBusniess = new WeixinQrcodeInfoBusiness();
		QrcodeInfoBean bean = qrcodeBusniess.getPermanentQrcodeInfoByAppSceneId(accountCode, appSceneId, callBackQueue);
		if (bean == null) {
			bean = new QrcodeInfoBean();
		}
		return bean.getTicket();
	}

	private String getAccessToken(String params) {
		HashMap<String, String> condMap = JsonKit.toBean(params, HashMap.class);
		String appCode = condMap.get("appCode");
		String storeCode = condMap.get("storeCode");
		// 获取accountID
		WeixinAccountInfoCache accountCache = new WeixinAccountInfoCache();
		int accountId = accountCache.getWeixinAccountInfoByCondition(appCode, storeCode).getAccountId();
		WeixinAccessTokenCache cache = new WeixinAccessTokenCache();
		return cache.getAccessToken(accountId);
	}

	private String getAccessTokenByAccountCode(String params) {
		HashMap<String, String> condMap = JsonKit.toBean(params, HashMap.class);
		String accountCode = condMap.get("accountCode");
		WeixinAccountInfoCache accountInfoCache = new WeixinAccountInfoCache();
		int accountId = accountInfoCache.getAccountIdByCode(accountCode);

		WeixinAccessTokenCache cache = new WeixinAccessTokenCache();
		return cache.getAccessToken(accountId);
	}

}
