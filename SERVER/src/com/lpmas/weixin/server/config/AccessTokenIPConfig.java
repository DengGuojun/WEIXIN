package com.lpmas.weixin.server.config;

import java.util.Set;

import com.lpmas.framework.util.PropertiesKit;
import com.lpmas.framework.util.SetKit;
import com.lpmas.weixin.config.WeixinConfig;

public class AccessTokenIPConfig {

	public static Set<String> allowIpSet = null;

	static {
		// 获取需要拦截的方法
		String allowIpStr = PropertiesKit.getBundleProperties(WeixinConfig.WEIXIN_PROP_FILE_NAME, "ALLOW_IPS");
		allowIpSet = SetKit.string2Set(allowIpStr, ";");
	}

}
