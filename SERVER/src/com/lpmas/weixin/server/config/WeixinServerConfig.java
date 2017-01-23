package com.lpmas.weixin.server.config;

import com.lpmas.framework.util.PropertiesKit;
import com.lpmas.weixin.config.WeixinConfig;

public class WeixinServerConfig {

	private static final String WORK_MODE_MASTER = "master";
	private static final String WORK_MODE_SLAVE = "slave";
	private static String WORK_MODE = PropertiesKit.getBundleProperties(WeixinConfig.WEIXIN_PROP_FILE_NAME,
			"WORK_MODE");
	public static boolean IS_MASTER_SERVER = false;

	static {
		if (WORK_MODE_MASTER.equalsIgnoreCase(WORK_MODE)) {
			IS_MASTER_SERVER = true;
		} else {
			IS_MASTER_SERVER = WORK_MODE_SLAVE.equals(WORK_MODE);
		}
	}

}
