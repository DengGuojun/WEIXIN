package com.lpmas.weixin.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.StatusKit;

public class WeixinConfig {
	// APP ID
	public static final String APP_ID = "Weixin";

	public static final String WEIXIN_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/weixin_config";
	public static final String WEIXIN_MONGO_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/weixin_mongo_config";

	// 公众号类型
	public static final Integer ACCOUNT_TYPE_SUBSCRIBE = 1;
	public static final Integer ACCOUNT_TYPE_SERVICE = 2;
	public static final Integer ACCOUNT_TYPE_ENTERPRISE = 3;
	public static List<StatusBean<Integer, String>> ACCOUNT_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
	public static HashMap<Integer, String> ACCOUNT_TYPE_MAP = new HashMap<Integer, String>();

	static {
		initAccountTypeList();
		initAcconutTypeMap();
	}

	private static void initAccountTypeList() {
		ACCOUNT_TYPE_LIST = new ArrayList<StatusBean<Integer, String>>();
		ACCOUNT_TYPE_LIST.add(new StatusBean<Integer, String>(ACCOUNT_TYPE_SUBSCRIBE, "订阅号"));
		ACCOUNT_TYPE_LIST.add(new StatusBean<Integer, String>(ACCOUNT_TYPE_SERVICE, "服务号"));
		ACCOUNT_TYPE_LIST.add(new StatusBean<Integer, String>(ACCOUNT_TYPE_ENTERPRISE, "企业号"));

	}

	private static void initAcconutTypeMap() {
		ACCOUNT_TYPE_MAP = StatusKit.toMap(ACCOUNT_TYPE_LIST);
	}

}
