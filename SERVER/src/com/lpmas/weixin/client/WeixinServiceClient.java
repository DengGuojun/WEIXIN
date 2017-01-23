package com.lpmas.weixin.client;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lpmas.framework.component.ComponentClient;
import com.lpmas.framework.util.JsonKit;
import com.lpmas.weixin.client.config.WeixinClientConfig;
import com.lpmas.weixin.component.WeixinServicePrx;
import com.lpmas.weixin.config.WeixinConfig;

public class WeixinServiceClient {

	private static Logger log = LoggerFactory.getLogger(WeixinServiceClient.class);

	private String rpc(String method, String params) {
		ComponentClient client = new ComponentClient();
		WeixinServicePrx pdmService = (WeixinServicePrx) client.getProxy(WeixinConfig.APP_ID, WeixinServicePrx.class);
		String result = pdmService.rpc(method, params);
		log.info("result : {}", result);
		return result;
	}

	public String getTemporaryQrcodeTicket(String appCode, String storeCode, String appSceneId) {
		return getTemporaryQrcodeTicket(appCode, storeCode, appSceneId, "", -1);
	}

	public String getTemporaryQrcodeTicket(String appCode, String storeCode, String appSceneId, String callBackQueue,
			int expireSeconds) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("appCode", appCode);
		param.put("storeCode", storeCode);
		param.put("appSceneId", appSceneId);
		param.put("callBackQueue", callBackQueue.trim());
		param.put("expireSeconds", String.valueOf(expireSeconds));
		return rpc(WeixinClientConfig.GET_TEMPORARY_QRCODE_TICKET, JsonKit.toJson(param));
	}

	public String getTemporaryQrcodeTicketByAccountCode(String accountCode, String appSceneId) {
		return getTemporaryQrcodeTicketByAccountCode(accountCode, appSceneId, "", -1);
	}

	public String getTemporaryQrcodeTicketByAccountCode(String accountCode, String appSceneId, String callBackQueue,
			int expireSeconds) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("accountCode", accountCode);
		param.put("appSceneId", appSceneId);
		param.put("callBackQueue", callBackQueue.trim());
		param.put("expireSeconds", String.valueOf(expireSeconds));
		return rpc(WeixinClientConfig.GET_TEMPORARY_QRCODE_TICKET_BY_ACCOUNT_CODE, JsonKit.toJson(param));
	}

	public String getPermanentTicketByAppSceneId(String appCode, String storeCode, String appSceneId) {
		return getPermanentTicketByAppSceneId(appCode, storeCode, appSceneId, "");
	}

	public String getPermanentTicketByAppSceneId(String appCode, String storeCode, String appSceneId,
			String callBackQueue) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("appCode", appCode);
		param.put("storeCode", storeCode);
		param.put("appSceneId", appSceneId);
		param.put("callBackQueue", callBackQueue.trim());
		return rpc(WeixinClientConfig.GET_PERMANENT_QRCODE_TICKET, JsonKit.toJson(param));
	}

	public String getPermanentTicketByAppSceneIdByAccountCode(String accountCode, String appSceneId) {
		return getPermanentTicketByAppSceneIdByAccountCode(accountCode, appSceneId, "");
	}

	public String getPermanentTicketByAppSceneIdByAccountCode(String accountCode, String appSceneId,
			String callBackQueue) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("accountCode", accountCode);
		param.put("appSceneId", appSceneId);
		param.put("callBackQueue", callBackQueue.trim());
		return rpc(WeixinClientConfig.GET_PERMANENT_QRCODE_TICKET_BY_ACCOUNT_CODE, JsonKit.toJson(param));
	}

	public String getAccessToken(String appCode, String storeCode) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("appCode", appCode);
		param.put("storeCode", storeCode);
		return rpc(WeixinClientConfig.GET_ACCESS_TOKEN, JsonKit.toJson(param));
	}

	public String getAccessTokenByAccountCode(String accountCode) {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("accountCode", accountCode);
		return rpc(WeixinClientConfig.GET_ACCESS_TOKEN_BY_ACCOUNT_CODE, JsonKit.toJson(param));
	}
}
