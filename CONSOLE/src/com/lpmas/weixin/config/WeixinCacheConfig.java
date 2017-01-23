package com.lpmas.weixin.config;

public class WeixinCacheConfig {

	// 缓存KEY配置
	public static final String WEIXIN_ACCESS_TOKEN_KEY = "WEIXIN_ACCESS_TOKEN_";
	public static final String WEIXIN_CONFIG_KEY = "WEIXIN_CONFIG_";
	public static final String WEIXIN_ACCOUNT_INFO_BY_CONDITION_KEY = "WEIXIN_ACCOUNT_INFO_BY_CONDITION_";
	public static final String WEIXIN_ACCOUNTCODE_KEY = "WEIXIN_ACCOUNTCODE_";
	public static final String WEIXIN_ACCOUNTID_KEY = "WEIXIN_ACCOUNTID_";
	public static final String WEIXIN_ACCOUNT_INFO_BY_CODE_KEY = "WEIXIN_ACCOUNT_INFO_BY_CODE_";
	public static final String AUTO_REPLY_MESSAGE_INFO_KEY = "AUTO_REPLY_MESSAGE_INFO_";

	// 缓存时间配置
	public static final int WEIXIN_ACCESS_TOKEN_TIME = 180;

	// 获取KEY方法
	public static String getWeixinAccessTokenCacheKey(String appId) {
		return WEIXIN_ACCESS_TOKEN_KEY + appId;
	}

	public static String getWeixinConfigCacheKey(int accountId) {
		return WEIXIN_CONFIG_KEY + accountId;
	}

	public static String getWeixinAccountInfoCacheKeyByCondition(String appCode, String storeCode) {
		return WEIXIN_ACCOUNT_INFO_BY_CONDITION_KEY + appCode + "_" + storeCode;
	}

	public static String getWeixinAccountCodeCacheKey(int accountId) {
		return WEIXIN_ACCOUNTCODE_KEY + accountId;
	}

	public static String getWeixinAccountIdCacheKey(String accountCode) {
		return WEIXIN_ACCOUNTID_KEY + accountCode;
	}

	public static String getWeixinAccountInfoCacheKeyByCode(String accountCode) {
		return WEIXIN_ACCOUNT_INFO_BY_CODE_KEY + accountCode;
	}

	public static String getAutoReplyMessageInfoListCacheKey(int accountId, int replyType) {
		return AUTO_REPLY_MESSAGE_INFO_KEY + accountId + "_" + replyType;
	}

}
