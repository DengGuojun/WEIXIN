package com.lpmas.weixin.console.config;

import com.lpmas.framework.util.PropertiesKit;
import com.lpmas.weixin.config.WeixinConfig;

public class WeixinDBConfig {

	public static String DB_LINK_WEIXIN_W = PropertiesKit.getBundleProperties(WeixinConfig.WEIXIN_PROP_FILE_NAME,
			"DB_LINK_WEIXIN_W");

	public static String DB_LINK_WEIXIN_R = PropertiesKit.getBundleProperties(WeixinConfig.WEIXIN_PROP_FILE_NAME,
			"DB_LINK_WEIXIN_R");

}
