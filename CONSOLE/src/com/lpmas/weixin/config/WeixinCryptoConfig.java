package com.lpmas.weixin.config;

import com.lpmas.framework.util.PropertiesKit;

public class WeixinCryptoConfig {

	public static final String AES_KEY_STR = PropertiesKit.getBundleProperties(WeixinConfig.WEIXIN_PROP_FILE_NAME,
			"WEIXIN_KEY_STR");

}
