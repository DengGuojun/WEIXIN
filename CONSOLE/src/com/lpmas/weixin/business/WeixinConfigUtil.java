package com.lpmas.weixin.business;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.crypto.AES;
import com.lpmas.framework.util.MapKit;
import com.lpmas.framework.web.UrlKit;
import com.lpmas.weixin.config.WeixinCryptoConfig;

public class WeixinConfigUtil {

	private Map<String, String> paramMap = new HashMap<String, String>();
	private static Logger log = LoggerFactory.getLogger(WeixinConfigUtil.class);

	public WeixinConfigUtil(String id) {
		try {
			// 解密
			String decodeId = AES.decrypt(id, WeixinCryptoConfig.AES_KEY_STR);
			// 变成Map
			paramMap = UrlKit.queryString2Map(decodeId);
		} catch (Exception e) {
			log.error("解密失败", e);
		}
	}

	public String getAccountCode() {
		return MapKit.getValueFromMap("accountCode", paramMap);
	}

	public static String getidentityString(String accountCode) {
		String identityString = "";
		identityString = "?id=" + AES.encrypt("accountCode=" + accountCode, WeixinCryptoConfig.AES_KEY_STR);
		return identityString;
	}

}
