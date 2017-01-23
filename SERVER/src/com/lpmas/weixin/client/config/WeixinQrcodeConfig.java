package com.lpmas.weixin.client.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lpmas.framework.bean.StatusBean;
import com.lpmas.framework.util.PropertiesKit;
import com.lpmas.framework.util.StatusKit;
import com.lpmas.weixin.config.WeixinConfig;

public class WeixinQrcodeConfig {

	// 提前多少秒刷新
	public static int SECONDS_BEFORE_EXPIRE = 300;
	// 临时二维码有效期
	public static int TICKET_EXPIRE = 2592000;

	// 二维码类型
	public static final String QRCODE_TYPE_TEMPORARY = "TEMPORARY";
	public static final String QRCODE_TYPE_PERMANENT = "PERMANENT";
	public static List<StatusBean<String, String>> QRCODE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
	public static Map<String, String> QRCODE_TYPE_MAP = new HashMap<String, String>();

	static {
		initQrcodeProperties();

		initQrcodeTypeList();
		initQrcodeTypeMap();
	}

	private static void initQrcodeProperties() {
		try {
			SECONDS_BEFORE_EXPIRE = Integer.valueOf(
					PropertiesKit.getBundleProperties(WeixinConfig.WEIXIN_PROP_FILE_NAME, "SECONDS_BEFORE_EXPIRE"));
		} catch (Exception e) {
			SECONDS_BEFORE_EXPIRE = 300;
		}

		try {
			TICKET_EXPIRE = Integer
					.valueOf(PropertiesKit.getBundleProperties(WeixinConfig.WEIXIN_PROP_FILE_NAME, "TICKET_EXPIRE"));
		} catch (Exception e) {
			TICKET_EXPIRE = 2592000;
		}
	}

	private static void initQrcodeTypeList() {
		QRCODE_TYPE_LIST = new ArrayList<StatusBean<String, String>>();
		QRCODE_TYPE_LIST.add(new StatusBean<String, String>(QRCODE_TYPE_TEMPORARY, "临时二维码"));
		QRCODE_TYPE_LIST.add(new StatusBean<String, String>(QRCODE_TYPE_PERMANENT, "永久二维码"));
	}

	private static void initQrcodeTypeMap() {
		QRCODE_TYPE_MAP = StatusKit.toMap(QRCODE_TYPE_LIST);
	}
}
