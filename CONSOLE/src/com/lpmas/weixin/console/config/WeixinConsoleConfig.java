package com.lpmas.weixin.console.config;

import com.lpmas.framework.config.Constants;
import com.lpmas.framework.util.PropertiesKit;
import com.lpmas.weixin.config.WeixinConfig;

public class WeixinConsoleConfig {

	// 设置文件保存路径
	public static final String FILE_PATH = PropertiesKit.getBundleProperties(WeixinConfig.WEIXIN_PROP_FILE_NAME,
			"FILE_PATH");
	public static final Integer DEFAULT_PAGE_NUM = 1;
	public static final Integer DEFAULT_PAGE_SIZE = 20;
	public static final String PAGE_PATH = Constants.PAGE_PATH + "weixin/";

}
