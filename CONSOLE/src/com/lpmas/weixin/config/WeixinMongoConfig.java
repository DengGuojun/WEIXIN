package com.lpmas.weixin.config;

import com.lpmas.framework.crypto.MD5;

public class WeixinMongoConfig {

	public static final String DB_NAME = "weixin";

	public static final String COLLECTION_RECEIVE_MESSAGE = "receive_message_info";
	public static final String COLLECTION_RECEIVE_EVENT = "receive_event_info";
	public static final String COLLECTION_USER_INFO = "weixin_user_info";
	public static final String COLLECTION_SEND_MESSAGE = "send_message_info";
	public static final String COLLECTION_QRCODE_INFO = "weixin_qrcode_info";
	public static final String COLLECTION_MATERIAL_INFO = "weixin_material_info";

	public static String getWeixinUserInfoKey(String openId, int accountId) {
		return MD5.getMD5(openId + "_" + accountId);
	}

	public static String getQrcodeInfoKey(int accountId, String appSceneId, String qrcodeType) {
		return MD5.getMD5(accountId + "_" + appSceneId + "_" + qrcodeType);
	}

	public static String getMaterilInfoKey(int accountId, String mediaType, String mediaId) {
		return MD5.getMD5(accountId + "_" + mediaType + "_" + mediaId);
	}

}
