package com.lpmas.weixin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.StatusKit;

public class WeixinAppConfig {

	public static final String CONFIG_KEY_APP_ID = "AppId";
	public static final String CONFIG_KEY_APP_SECRET = "AppSecret";
	public static final String CONFIG_KEY_TOKEN = "Token";
	public static final String CONFIG_KEY_CRYPTO_KEY = "CryptoKey";
	public static final String CONFIG_KEY_QUEUE_NAME = "QueueName";
	public static final String WEIXIN_ACCOUNT_CONFIG_PREFIX = "WEIXIN_ACCOUNT_INFO";
	public static List<StatusBean<String, String>> CONFIG_KEY_LIST = new ArrayList<StatusBean<String, String>>();
	public static HashMap<String, String> CONFIG_KEY_MAP = new HashMap<String, String>();

	static {
		initConfigKeyList();
		initConfigKeyMap();
	}

	private static void initConfigKeyList() {
		CONFIG_KEY_LIST = new ArrayList<StatusBean<String, String>>();
		CONFIG_KEY_LIST.add(new StatusBean<String, String>(CONFIG_KEY_APP_ID, "微信公众号AppID"));
		CONFIG_KEY_LIST.add(new StatusBean<String, String>(CONFIG_KEY_APP_SECRET, "微信公众号Secret"));
		CONFIG_KEY_LIST.add(new StatusBean<String, String>(CONFIG_KEY_TOKEN, "微信公众号消息接入Token"));
		CONFIG_KEY_LIST.add(new StatusBean<String, String>(CONFIG_KEY_CRYPTO_KEY, "微信公众号消息接入加密密钥"));
		CONFIG_KEY_LIST.add(new StatusBean<String, String>(CONFIG_KEY_QUEUE_NAME, "消息分发队列名"));
	}

	private static void initConfigKeyMap() {
		CONFIG_KEY_MAP = StatusKit.toMap(CONFIG_KEY_LIST);
	}

	public static String getWeixinConfigCode(int accountId, String configKey) {
		return WEIXIN_ACCOUNT_CONFIG_PREFIX + "_" + accountId + "_" + configKey;
	}
}
