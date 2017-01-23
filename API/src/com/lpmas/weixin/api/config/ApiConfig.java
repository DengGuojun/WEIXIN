package com.lpmas.weixin.api.config;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.PropertiesKit;

public class ApiConfig {
	public static final String WEIXIN_API_PROP_FILE_NAME = Constants.PROP_FILE_PATH + "/weixin_api_config";

	public static final int DEFAULT_CONNECTION_TIMEOUT = Integer
			.parseInt(PropertiesKit.getBundleProperties(WEIXIN_API_PROP_FILE_NAME, "DEFAULT_CONNECTION_TIMEOUT"));
	public static final int DEFAULT_SOCKET_TIMEOUT = Integer
			.parseInt(PropertiesKit.getBundleProperties(WEIXIN_API_PROP_FILE_NAME, "DEFAULT_SOCKET_TIMEOUT"));
	public static final String DEFAULT_CHARSET = PropertiesKit.getBundleProperties(WEIXIN_API_PROP_FILE_NAME,
			"DEFAULT_CHARSET");

}
